package com.example.domain.ndb;

/**
 * Created by bsheen on 6/12/17.
 */
public class Item {
    private String name;
    private String ndbno;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNdbno() {
        return ndbno;
    }

    public void setNdbno(String ndbno) {
        this.ndbno = ndbno;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", ndbno='" + ndbno + '\'' +
                '}';
    }
}
