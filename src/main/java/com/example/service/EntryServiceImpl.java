package com.example.service;

import com.example.domain.Carb;
import com.example.domain.Entry;
import com.example.domain.User;
import com.example.repository.EntryRepository;
import groovy.transform.TailRecursive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bsheen on 6/5/17.
 */

@Service
public class EntryServiceImpl implements EntryService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    @Autowired
    EntryRepository entryRepository;

    @Override
    @Transactional(readOnly = true)
    public Entry getNewEntry() {
        Entry entry = new Entry();
        LocalDate ld = LocalDate.now(ZoneId.of("America/Chicago"));
        LocalTime lt = LocalTime.now(ZoneId.of("America/Chicago"));
        String date = ld.format(DATE_FORMATTER);
        String time = lt.format(TIME_FORMATTER);
        ld = LocalDate.parse(date, DATE_FORMATTER);
        lt = LocalTime.parse(time, TIME_FORMATTER);
        entry.setDate(ld);
        entry.setTime(lt);
        return entry;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    @Override
    @Transactional
    public Entry addEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    @Transactional(readOnly = true)
    public Entry findEntry(Integer eid) {
        Entry entry = entryRepository.findOne(eid);
        return entry;
    }

    @Override
    @Transactional
    public Entry updateEntry(Entry entry, Integer eid) {
        Entry entryInDb = entryRepository.findOne(eid);
        entryInDb.setBloodsugar(entry.getBloodsugar());
        entryInDb.setInsulin(entry.getInsulin());
        entryInDb.setBolus(entry.getBolus());
        entryInDb.setDate(entry.getDate());
        entryInDb.setTime(entry.getTime());
        updateEntryTotCarbs(eid);
        return entryRepository.save(entryInDb);
    }

    @Override
    @Transactional
    public Entry updateEntryTotCarbs(Integer eid) {
        Entry entry = entryRepository.findOne(eid);
        Integer totCarbs = 0;
        if(entry.getCarbs()!=null && entry.getCarbs().size()>0){
            for(Carb carb: entry.getCarbs()){
                if(carb.getTotalCarbs()!=null){
                    totCarbs+=carb.getTotalCarbs();
                }
            }
        }
        if(totCarbs!=0) {
            entry.setTotalCarbs(totCarbs);
        }
        return entryRepository.save(entry);
    }

    @Override
    @Transactional
    public void deleteEntry(Integer eid) {
        entryRepository.delete(eid);
    }

    @Override
    @Transactional
    public void deleteEntries(List<Integer> eids) {
        if(eids!=null){
            if(eids.size()>0) {
                for (Integer eid : eids) {
                    entryRepository.delete(eid);
                }
            }
        }
    }
}
