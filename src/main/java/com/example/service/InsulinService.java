package com.example.service;

import com.example.domain.Insulin;
import com.example.domain.User;

import java.util.List;

/**
 * Created by bsheen on 6/6/17.
 */
public interface InsulinService {
    public List<Insulin> findAll();

    public Insulin getBolus(String username, Integer eid);

    public Insulin addInsulin(Insulin insulin, Integer eid);

    public void delete(Integer iid);
}
