package com.example.domain.ndb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by bsheen on 6/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Food {

    private String name;
    private List<Nutrient> nutrients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", nutrients=" + (nutrients == null ? "" : nutrients) +
                '}';
    }
}
