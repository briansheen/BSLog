package com.example.controller;

import com.example.domain.Carb;
import com.example.domain.Entry;
import com.example.domain.Insulin;
import com.example.domain.User;
import com.example.domain.ndb.*;
import com.example.service.*;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Created by bsheen on 6/1/17.
 */

@Controller
public class BSLogController {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    UserService userService;

    @Autowired
    EntryService entryService;

    @Autowired
    CarbService carbService;

    @Autowired
    NdbService ndbService;

    @Autowired
    InsulinService insulinService;

    @GetMapping("/dash")
    public String dash(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        if (userService.findByUsername(currentPrincipalName) == null) {
            userService.addUser(currentPrincipalName);
        }

        Date today = new Date();
        model.addAttribute("date", SimpleDateFormat.getDateInstance(DateFormat.MEDIUM).format(today));
        model.addAttribute("entryList", userService.getEntriesByUserToday(currentPrincipalName));

        return "dash";
    }

    @PostMapping("/dash")
    public String deleteEntries(Model model, @RequestParam(value = "deleteMe", required = false) List<Integer> eids) {
        entryService.deleteEntries(eids);
        return "redirect:/dash";
    }

    @GetMapping("/addEntry")
    public String addEntry(Model model) {
        Entry entry = new Entry();
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        String date = ld.format(DATE_FORMATTER);
        String time = lt.format(TIME_FORMATTER);
        ld = LocalDate.parse(date, DATE_FORMATTER);
        lt = LocalTime.parse(time, TIME_FORMATTER);
        entry.setDate(ld);
        entry.setTime(lt);
        model.addAttribute("entry", entry);
        return "addEntry";
    }

    @PostMapping("/addEntry")
    public String entrySubmit(Model model, Entry entry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        entry.setUser(userService.findByUsername(currentPrincipalName));
        entryService.addEntry(entry);
        model.addAttribute("entry", entry);
        return "entry";
    }

    @GetMapping("/viewEntry/{eid}")
    public String viewEntry(Model model, @PathVariable("eid") Integer eid) {
        Entry entry = entryService.findEntry(eid);
        model.addAttribute("entry", entry);
        model.addAttribute("carbList", entry.getCarbs());
        return "entry";
    }

    @GetMapping("/editEntry/{eid}")
    public String editEntry(Model model, @PathVariable("eid") Integer eid) {
        Entry entry = entryService.findEntry(eid);
        model.addAttribute("insulin", entry.getInsulin());
        model.addAttribute("entry", entry);
        model.addAttribute("carbList", entry.getCarbs());
        return "editEntry";
    }

    @PostMapping("/editEntry/{eid}")
    public String editEntrySubmit(Model model, @PathVariable("eid") Integer eid, Entry entry) {
        entryService.updateEntry(entry, eid);
        return "redirect:/viewEntry/" + eid;
    }

    @GetMapping("/entry/{eid}/addCarbs")
    public String addCarbToEntry(Model model, @PathVariable("eid") Integer eid) {
        Entry entry = entryService.findEntry(eid);
        model.addAttribute("carbList", carbService.findAllByEntry(entry));
        Carb carb = new Carb();
        model.addAttribute("entry", entry);
        model.addAttribute("carb", carb);
        return "addCarbs";
    }

    @PostMapping("/entry/{eid}/deleteCarbs")
    public String deleteCarbs(Model model, @PathVariable("eid") Integer eid, @RequestParam(value = "deleteMe", required = false) List<Integer> cids) {
        carbService.deleteCarbs(cids);
        return "redirect:/entry/" + eid + "/addCarbs";
    }

    @PostMapping("/entry/{eid}/addCarbs")
    public String carbToEntrySubmit(Model model, @PathVariable("eid") Integer eid, @RequestParam(value = "deleteMe", required = false) List<Integer> cids, Carb carb) {
        Entry entry = entryService.findEntry(eid);
        carb.setEntry(entry);
        carbService.addCarb(carb);
        return "redirect:/entry/" + eid + "/addCarbs";
    }

    @PostMapping("/entry/{eid}/search")
    public String searchCarbToEntry(Model model, @PathVariable("eid") Integer eid, @RequestParam(value = "query", required = true) String query) {
        NdbSearchResponse ndbSearchResponse = ndbService.search(query);
        model.addAttribute("searchResponse", ndbSearchResponse);
        model.addAttribute("eid", eid);
        return "selectCarbFromSearch";
    }

    @PostMapping("/entry/{eid}/addCarbsFromSearch")
    public String addCarbFromSearch(Model model, @PathVariable("eid") Integer eid, @RequestParam(value = "ndbno", required = true) String ndbno) {
        NdbResponse ndbResponse = ndbService.get(ndbno);
        Nutrient nutrient = ndbService.findById("Carbohydrate", ndbResponse.getReport().getFood().getNutrients());
        model.addAttribute("nutrient", nutrient);
        model.addAttribute("ndbno", ndbno);
        model.addAttribute("ndbResponse", ndbResponse);
        model.addAttribute("eid", eid);
        return "selectServingFromGet";
    }

    @PostMapping("/entry/{eid}/refineServingFromSearch")
    public String addServingFromSearch(Model model, @PathVariable("eid") Integer eid, @RequestParam(value = "label", required = true) String label, @RequestParam(value = "ndbno", required = true) String ndbno) {
        NdbResponse ndbResponse = ndbService.get(ndbno);
        Nutrient nutrient = ndbService.findById("Carbohydrate", ndbResponse.getReport().getFood().getNutrients());
        Carb carb = new Carb();
        carb.setName(ndbResponse.getReport().getFood().getName());
        carb.setEntry(entryService.findEntry(eid));
        String servingSize = null;
        String carbsPerServing = null;
        for (Measure measure : nutrient.getMeasures()) {
            if (measure.getLabel().equals(label)) {
                servingSize = String.valueOf(measure.getQty()) + " " + measure.getLabel();
                carbsPerServing = measure.getValue();
                break;
            }
        }
        carb.setServingSize(servingSize);
        carb.setCarbsPerServing((int) Double.parseDouble(carbsPerServing));
        model.addAttribute("carb", carb);
        model.addAttribute("eid", eid);
        return "enterServingFromGet";
    }

    @PostMapping("/entry/{eid}/enterCarbsFromSearch")
    public String enterCarbFromSearch(Model model, @PathVariable("eid") Integer eid, Carb carb) {
        Entry entry = entryService.findEntry(eid);
        carb.setEntry(entry);
        carb.setTotalCarbs(carb.getNumServings() * carb.getCarbsPerServing());
        carbService.addCarb(carb);
        return "redirect:/entry/" + eid + "/addCarbs";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(currentPrincipalName);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/editProfile")
    public String editProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(currentPrincipalName);
        model.addAttribute("user", user);
        return "editProfile";
    }

    @PostMapping("/editProfile")
    public String editProfileSubmit(Model model, User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User userInDb = userService.findByUsername(currentPrincipalName);
        userInDb.setIcr(user.getIcr());
        userInDb.setIsr(user.getIsr());
        userInDb.setUpdatedAt(LocalDateTime.now());
        userService.updateUserSettings(userInDb);
        return "redirect:/profile";
    }

    @PostMapping("/entry/{eid}/addInsulin")
    public String getBolusSuggestionForEntry(Model model, @PathVariable("eid") Integer eid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Insulin insulin = insulinService.getBolus(currentPrincipalName, eid);
        Entry entry = entryService.findEntry(eid);
        model.addAttribute("entry", entry);
        model.addAttribute("insulin", insulin);
        return "addInsulin";
    }

    @PostMapping("/entry/{eid}/submitInsulin")
    public String addInsulinToEntry(Model model, @PathVariable("eid") Integer eid, Insulin insulin) {
        insulin.setEntry(entryService.findEntry(eid));
        Entry entry = entryService.findEntry(eid);
        Insulin insulinToAdd = insulinService.addInsulin(insulin,eid);
        entry.setInsulin(insulinToAdd);
        entryService.updateEntry(entry, eid);
        return "redirect:/dash";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
