package com.example.domain;

import javax.persistence.*;

/**
 * Created by bsheen on 6/6/17.
 */

@Entity
@Table(name="insulin")
public class Insulin {
    private Integer iid;
    private Double insulin;
    private Entry entry;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Double getInsulin() {
        return insulin;
    }

    public void setInsulin(Double insulin) {
        this.insulin = insulin;
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

        return iid.equals(insulin.iid);
    }

    @Override
    public int hashCode() {
        return iid.hashCode();
    }
}
