package com.example.service;

import com.example.domain.Insulin;
import com.example.repository.InsulinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bsheen on 6/6/17.
 */

@Service
public class InsulinServiceImpl implements InsulinService {

    @Autowired
    InsulinRepository insulinRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Insulin> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Insulin addInsulin(Insulin insulin) {
        return insulinRepository.save(insulin);
    }
}
