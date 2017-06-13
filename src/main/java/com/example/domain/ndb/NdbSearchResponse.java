package com.example.domain.ndb;

/**
 * Created by bsheen on 6/12/17.
 */
public class NdbSearchResponse {

    private FoodList list;

    public FoodList getList() {
        return list;
    }

    public void setList(FoodList list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "NdbSearchResponse{" +
                "list=" + (list == null ? "" : list) +
                '}';
    }
}
