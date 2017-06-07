package com.example.domain;

import javax.persistence.*;

/**
 * Created by bsheen on 6/6/17.
 */

@Entity
@Table(name="bloodsugar")
public class Bloodsugar {
    private Integer bsid;
    private Integer bloodsugar;
    private Entry entry;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getBsid() {
        return bsid;
    }

    public void setBsid(Integer bsid) {
        this.bsid = bsid;
    }

    public Integer getBloodsugar() {
        return bloodsugar;
    }

    public void setBloodsugar(Integer bloodsugar) {
        this.bloodsugar = bloodsugar;
    }

    @OneToOne
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

        Bloodsugar that = (Bloodsugar) o;

        return bsid.equals(that.bsid);
    }

    @Override
    public int hashCode() {
        return bsid.hashCode();
    }

    @Override
    public String toString() {
        return "Bloodsugar{" +
                "bsid=" + bsid +
                ", bloodsugar=" + bloodsugar +
                ", entry=" + (entry == null ? "" : entry.getEid()) +
                '}';
    }
}
