package com.example.domain.ndb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by bsheen on 6/12/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nutrient {
    private String nutrientId;
    private String name;
    private String unit; //100g
    private String value; //carbs per 100g
    private List<Measure> measures;

    public String getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(String nutrientId) {
        this.nutrientId = nutrientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    @Override
    public String toString() {
        return "Nutrient{" +
                "nutrientId='" + nutrientId + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", value='" + value + '\'' +
                ", measures=" + (measures == null ? "" : measures) +
                '}';
    }
}
