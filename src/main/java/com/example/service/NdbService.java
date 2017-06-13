package com.example.service;

import com.example.domain.ndb.NdbResponse;
import com.example.domain.ndb.NdbSearchResponse;
import com.example.domain.ndb.Nutrient;

import java.util.List;

/**
 * Created by bsheen on 6/12/17.
 */
public interface NdbService {
    NdbSearchResponse search(String filter);
    NdbResponse get(String ndbno);
    Nutrient findById(String name, List<Nutrient> nutrients);
}
