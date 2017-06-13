package com.example.controller;

import com.example.domain.Carb;
import com.example.domain.Entry;
import com.example.domain.User;
import com.example.domain.ndb.FoodList;
import com.example.domain.ndb.NdbResponse;
import com.example.domain.ndb.NdbSearchResponse;
import com.example.domain.ndb.Nutrient;
import com.example.service.CarbService;
import com.example.service.EntryService;
import com.example.service.NdbService;
import com.example.service.UserService;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/dash")
    public String dash(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        model.addAttribute("entryList",entryService.getTotCarbPerEntryByUser(userService.findByUsername(currentPrincipalName)));

        return "dash";
    }

    @GetMapping("/addEntry")
    public String addEntry(Model model){
        Entry entry = new Entry();

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();

        String date=ld.format(DATE_FORMATTER);
        String time=lt.format(TIME_FORMATTER);

        ld = LocalDate.parse(date, DATE_FORMATTER);
        lt=LocalTime.parse(time,TIME_FORMATTER);

        entry.setDate(ld);
        entry.setTime(lt);

        model.addAttribute("entry", entry);

        return "addEntry";
    }

    @PostMapping("/addEntry")
    public String entrySubmit(Model model, Entry entry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        entry.setUser(userService.findByUsername(currentPrincipalName));
        entryService.addEntry(entry);

        model.addAttribute("entry",entry);

        return "entry";
    }

    @GetMapping("/editEntry/{eid}")
    public String editEntry(Model model, @PathVariable("eid") Integer eid){
        Entry entry = entryService.findEntry(eid);
        model.addAttribute("entry", entry);
        return "entry";
    }

    @GetMapping("/entry/{eid}/addCarbs")
    public String addCarbToEntry(Model model, @PathVariable("eid") Integer eid){
        Entry entry = entryService.findEntry(eid);
        model.addAttribute("carbList",carbService.findAllByEntry(entry));
        Carb carb = new Carb();
        model.addAttribute("entry",entry);
        model.addAttribute("carb",carb);
        FoodList foodList = new FoodList();
        model.addAttribute("foodList", foodList);
        return "addCarbs";
    }

    @PostMapping("/entry/{eid}/addCarbs")
    public String carbToEntrySubmit(Model model, @PathVariable("eid") Integer eid, Carb carb){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Entry entry = entryService.findEntry(eid);
        carb.setEntry(entry);
        carbService.addCarb(carb);

        return "redirect:/entry/"+eid+"/addCarbs";
    }

    @PostMapping("/entry/{eid}/search")
    public String searchCarbToEntry(Model model, @PathVariable("eid") Integer eid, @RequestParam(value="query", required = true) String query){

        NdbSearchResponse ndbSearchResponse = ndbService.search(query);

        model.addAttribute("searchResponse", ndbSearchResponse);
        model.addAttribute("eid", eid);

        return "selectCarbFromSearch";
    }

    @PostMapping("/entry/{eid}/addCarbsFromSearch")
    public String addCarbFromSearch(Model model, @PathVariable("eid") Integer eid, @RequestParam(value="ndbno", required=true) String ndbno){
        NdbResponse ndbResponse = ndbService.get(ndbno);

        Nutrient nutrient = ndbService.findById("Carbohydrate",ndbResponse.getReport().getFood().getNutrients());

        model.addAttribute("nutrient",nutrient);
        model.addAttribute("ndbResponse", ndbResponse);
        model.addAttribute("eid",eid);
        return "selectServingFromGet";
    }

    @GetMapping("/entry/{eid}/addCarbs/{q}")
    public String searchNdbForEntry(Model model, @PathVariable("eid") Integer eid, @PathVariable("q") String q){
        NdbSearchResponse ndbSearchResponse = ndbService.search(q);
        model.addAttribute("searchResponse", ndbSearchResponse);
        return "selectCarbFromSearch";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
