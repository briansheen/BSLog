package com.example.service;

import com.example.domain.Insulin;

import java.util.List;

/**
 * Created by bsheen on 6/6/17.
 */
public interface InsulinService {
    public List<Insulin> findAll();

    public Insulin addInsulin(Insulin insulin);
}
