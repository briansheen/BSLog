package com.example.service;

import com.example.domain.Carb;

import java.util.List;

/**
 * Created by bsheen on 6/5/17.
 */
public interface CarbService {

    public List<Carb> findAll();

    public Carb addCarb(Carb carb);
}
