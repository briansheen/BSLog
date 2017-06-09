package com.example.service;

import com.example.domain.Carb;
import com.example.domain.Entry;

import java.util.List;

/**
 * Created by bsheen on 6/5/17.
 */
public interface CarbService {

    public List<Carb> findAll();

    public Carb addCarb(Carb carb);

    public List<Carb> findAllByEntry(Entry entry);
}
