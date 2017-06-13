package com.example.domain.ndb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bsheen on 6/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Measure {

    private String label;//serving label
    private double eqv; //serving size
    private String eunit; //serving size unit
    private double qty; //num servings
    private String value; //carbs per serving

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getEqv() {
        return eqv;
    }

    public void setEqv(double eqv) {
        this.eqv = eqv;
    }

    public String getEunit() {
        return eunit;
    }

    public void setEunit(String eunit) {
        this.eunit = eunit;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "label='" + label + '\'' +
                ", eqv=" + eqv +
                ", eunit='" + eunit + '\'' +
                ", qty=" + qty +
                ", value='" + value + '\'' +
                '}';
    }
}
