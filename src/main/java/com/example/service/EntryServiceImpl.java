package com.example.service;

import com.example.domain.Carb;
import com.example.domain.Entry;
import com.example.domain.User;
import com.example.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bsheen on 6/5/17.
 */

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    EntryRepository entryRepository;

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
    @Transactional(readOnly = true)
    public Entry updateEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    @Transactional
    public List<Entry> getTotCarbPerEntryByUser(User user){
        Integer totcarbs = 0;
        if(entryRepository.findAll().size()>0) {
            for (Entry entry : entryRepository.findAll()) {
                if(entry.getCarbs().size()>0){
                    for (Carb carb : entry.getCarbs()) {
                        if(carb.getTotalCarbs()!=null) {
                            totcarbs += carb.getTotalCarbs();
                        }
                    }
                    entry.setTotalCarbs(totcarbs);
                    totcarbs=0;
                }
            }
        }
        return entryRepository.findAll();
    }

    @Override
    public void deleteEntry(Integer eid) {
        entryRepository.delete(eid);
    }
}
