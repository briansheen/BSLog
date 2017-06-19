package com.example.domain;

import javax.persistence.*;

/**
 * Created by bsheen on 6/6/17.
 */

@Entity
@Table(name="insulin")
public class Insulin {
    private Integer iid;
    private Double bolus;
    private Entry entry;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Double getBolus() {
        return bolus;
    }

    public void setBolus(Double bolus) {
        this.bolus = bolus;
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

        Insulin insulin = (Insulin) o;

        return iid != null ? iid.equals(insulin.iid) : insulin.iid == null;
    }

    @Override
    public int hashCode() {
        return iid != null ? iid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Insulin{" +
                "iid=" + iid +
                ", bolus=" + bolus +
                ", entry=" + (entry == null ? "" : entry.getEid()) +
                '}';
    }
}
