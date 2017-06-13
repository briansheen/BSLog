package com.example.service;

import com.example.domain.ndb.NdbResponse;
import com.example.domain.ndb.NdbSearchResponse;
import com.example.domain.ndb.Nutrient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by bsheen on 6/12/17.
 */

@Service
public class NdbServiceImpl implements NdbService {


    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public NdbSearchResponse search(String filter) {
        return restTemplate.getForObject("https://api.nal.usda.gov/ndb/search/?format=json&q=" + filter + "&sort=r&offset=0&api_key=Z6DdyaA22C4HG7N8yCR2ivKqE5TCNOc5GDqOYUoS", NdbSearchResponse.class);
    }

    @Override
    public NdbResponse get(String ndbno) {
        return restTemplate.getForObject("https://api.nal.usda.gov/ndb/reports/?ndbno=" + ndbno + "&type=b&format=json&api_key=Z6DdyaA22C4HG7N8yCR2ivKqE5TCNOc5GDqOYUoS", NdbResponse.class);
    }

    @Override
    public Nutrient findById(String name, List<Nutrient> nutrients) {
        for (Nutrient nutrient : nutrients) {
            if (nutrient.getName().contains(name)) {
                return nutrient;
            }
        }
        return null;
    }
}
