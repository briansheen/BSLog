package com.example.service;

import com.example.domain.Bloodsugar;

import java.util.List;

/**
 * Created by bsheen on 6/6/17.
 */
public interface BloodsugarService {

    public List<Bloodsugar> findAll();

    public Bloodsugar addBloodsugar(Bloodsugar bloodsugar);
}
