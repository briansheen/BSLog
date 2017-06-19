package com.example.service;

import com.example.domain.Carb;
import com.example.domain.Entry;
import com.example.repository.CarbRepository;
import com.example.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bsheen on 6/5/17.
 */

@Service
public class CarbServiceImpl implements CarbService {

    @Autowired
    CarbRepository carbRepository;

    @Autowired
    EntryRepository entryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Carb> findAll() {
        return carbRepository.findAll();
    }

    @Override
    @Transactional
    public Carb addCarb(Carb carb) {
        carb = carbRepository.save(carb);
        Entry entry = entryRepository.findOne(carb.getEntry().getEid());
        Integer totcarbs = 0;
        if (entry.getCarbs() != null) {
            for(Carb c : entry.getCarbs()){
                if(c.getTotalCarbs()!=null){
                    totcarbs += c.getTotalCarbs();
                }
            }
        }
        if(totcarbs!=0){
            entry.setTotalCarbs(totcarbs);
        }
        entryRepository.save(entry);
        return carb;
    }

    @Override
    @Transactional
    public void deleteCarb(Integer cid) {
        carbRepository.delete(cid);
    }

    @Override
    @Transactional
    public void deleteCarbs(List<Integer> cids, Integer eid) {
        if(cids!=null){
            if(cids.size()>0) {
                for (Integer cid : cids) {
                    deleteCarb(cid);
                }
            }
        }
        updateCarbsInEntry(eid);
    }

    @Override
    @Transactional
    public void updateCarbsInEntry(Integer eid){
        Entry entry = entryRepository.findOne(eid);
        Integer totcarbs = 0;
        if (entry.getCarbs() != null) {
            for(Carb c : entry.getCarbs()){
                if(c.getTotalCarbs()!=null){
                    totcarbs += c.getTotalCarbs();
                }
            }
        }
        if(totcarbs == 0){
            entry.setTotalCarbs(null);
        }
        if(totcarbs!=0){
            entry.setTotalCarbs(totcarbs);
        }
        entryRepository.save(entry);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carb> findAllByEntry(Entry entry) {
        return entryRepository.findOne(entry.getEid()).getCarbs();
    }
}
