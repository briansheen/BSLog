package com.example.domain;

import javax.persistence.*;

/**
 * Created by bsheen on 6/4/17.
 */

@Entity
@Table(name="carb")
public class Carb {
    private Integer cid;
    private String name;
    private String servingSize;
    private Integer carbsPerServing;
    private Integer numServings;
    private Integer totalCarbs;
    private Entry entry; //for the entry entry_id

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public Integer getCarbsPerServing() {
        return carbsPerServing;
    }

    public void setCarbsPerServing(Integer carbsPerServing) {
        this.carbsPerServing = carbsPerServing;
    }

    public Integer getNumServings() {
        return numServings;
    }

    public void setNumServings(Integer numServings) {
        this.numServings = numServings;
    }

    public Integer getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(Integer totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    @ManyToOne
    @JoinColumn(name="entry_id")
    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carb carb = (Carb) o;

        return cid != null ? cid.equals(carb.cid) : carb.cid == null;
    }

    @Override
    public int hashCode() {
        return cid != null ? cid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Carb{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", servingSize='" + servingSize + '\'' +
                ", carbsPerServing=" + carbsPerServing +
                ", numServings=" + numServings +
                ", totalCarbs=" + totalCarbs +
                ", entry=" + (entry == null ? "" : entry.getEid()) +
                '}';
    }
}
