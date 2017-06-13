package com.example.domain.ndb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bsheen on 6/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Report {

    private Food food;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Report{" +
                "food=" + food +
                '}';
    }
}
