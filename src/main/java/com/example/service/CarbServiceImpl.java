package com.example.service;

import com.example.domain.Carb;
import com.example.repository.CarbRepository;
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

    @Override
    @Transactional(readOnly = true)
    public List<Carb> findAll() {
        return carbRepository.findAll();
    }

    @Override
    @Transactional
    public Carb addCarb(Carb carb) {
        return carbRepository.save(carb);
    }
}
