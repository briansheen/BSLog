package com.example.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bsheen on 6/1/17.
 */

@Entity
@Table(name="entry")
public class Entry {
    private Integer eid;
    private LocalDateTime dateTime;
    private User user; //for the user username
    private List<Carb> carbs;
    private Insulin insulin;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @ManyToOne()
    @JoinColumn(name="username")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "entry", fetch = FetchType.LAZY)
    public List<Carb> getCarbs() {
        return carbs;
    }

    public void setCarbs(List<Carb> carbs) {
        this.carbs = carbs;
    }

    @OneToOne(mappedBy = "entry", fetch = FetchType.LAZY)
    public Insulin getInsulin() {
        return insulin;
    }

    public void setInsulin(Insulin insulin) {
        this.insulin = insulin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        return eid.equals(entry.eid);
    }

    @Override
    public int hashCode() {
        return eid.hashCode();
    }


    @Override
    public String toString() {
        return "Entry{" +
                "eid=" + eid +
                ", dateTime=" + dateTime +
                ", user=" + (user == null ? "" : user.getUsername()) +
                ", carbs=" + carbs +
                '}';
    }
}
