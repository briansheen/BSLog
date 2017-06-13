package com.example.domain.ndb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by bsheen on 6/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodList {

    private String q;
    private String ds;
    private List<Item> item;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "FoodList{" +
                "q='" + q + '\'' +
                ", ds='" + ds + '\'' +
                ", item=" + (item == null ? "" : item) +
                '}';
    }
}
