package com.example.service;

import com.example.domain.Bloodsugar;
import com.example.repository.BloodsugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bsheen on 6/6/17.
 */
@Service
public class BloodsugarServiceImpl implements BloodsugarService {

    @Autowired
    BloodsugarRepository bloodsugarRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Bloodsugar> findAll() {
        return bloodsugarRepository.findAll();
    }

    @Override
    @Transactional
    public Bloodsugar addBloodsugar(Bloodsugar bloodsugar) {
        return bloodsugarRepository.save(bloodsugar);
    }
}
