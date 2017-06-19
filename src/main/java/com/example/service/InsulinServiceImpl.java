package com.example.service;

import com.example.domain.Entry;
import com.example.domain.Insulin;
import com.example.domain.User;
import com.example.repository.EntryRepository;
import com.example.repository.InsulinRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by bsheen on 6/6/17.
 */

@Service
public class InsulinServiceImpl implements InsulinService {

    @Autowired
    InsulinRepository insulinRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Insulin> findAll() {
        return insulinRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Insulin getBolus(String username, Integer eid) {
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.DOWN);
        User user = userRepository.getOne(username);
        Entry entry = entryRepository.findOne(eid);
        Insulin insulin = new Insulin();
        insulin.setEntry(entryRepository.findOne(eid));
        Double bolus = null;
        if (entry.getTotalCarbs() != null) {
            if (entry.getBloodsugar() > 100) {
                bolus = (((double) entry.getTotalCarbs() / user.getIcr()) + (((double) entry.getBloodsugar() - 100) / user.getIsr()));
            } else {
                bolus = (((double) entry.getTotalCarbs() / user.getIcr()) - ((100 - (double) entry.getBloodsugar()) / user.getIsr()));
            }
            bolus = Double.valueOf(df.format(bolus));
        } else {
            if (entry.getBloodsugar() > 100) {
                bolus = ((double) entry.getBloodsugar() - 100) / user.getIsr();
                bolus = Double.valueOf(df.format(bolus));
            }
        }
        if(bolus!=null){
            if (bolus > (double) 0) {
                insulin.setBolus(bolus);
            } else {
                insulin.setBolus((double)0);
            }
        } else {
            insulin.setBolus((double) 0);
        }
        return insulin;
    }

    @Override
    @Transactional
    public Insulin addInsulin(Insulin insulin, Integer eid) {
        Entry entry = entryRepository.findOne(eid);
        if (entry.getInsulin() != null) {
            Insulin entryInsulin = entry.getInsulin();
            if (insulinRepository.exists(entryInsulin.getIid())) {
                delete(entryInsulin.getIid());
            }
        }
        entry.setBolus(insulin.getBolus());
        entryRepository.save(entry);
        return insulinRepository.save(insulin);
    }

    @Override
    @Transactional
    public void delete(Integer iid) {
        insulinRepository.delete(iid);
        insulinRepository.flush();
    }
}
