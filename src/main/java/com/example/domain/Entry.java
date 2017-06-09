package com.example.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by bsheen on 6/1/17.
 */

@Entity
@Table(name="entry")
public class Entry {

    private Integer eid;
    private LocalDate date;
    private LocalTime time;
    private User user; //for the user username
    private Integer totalCarbs;
    private List<Carb> carbs;
    private Insulin insulin;

    @NotNull
    private Integer bloodsugar;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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

    public Integer getBloodsugar() {
        return bloodsugar;
    }

    public void setBloodsugar(Integer bloodsugar) {
        this.bloodsugar = bloodsugar;
    }

    public Integer getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(Integer totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        return eid != null ? eid.equals(entry.eid) : entry.eid == null;
    }

    @Override
    public int hashCode() {
        return eid != null ? eid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "eid=" + eid +
                ", date=" + date +
                ", time=" + time +
                ", user=" + (user == null ? "" : user.getUsername()) +
                ", totalCarbs=" + totalCarbs +
                ", carbs=" + carbs +
                ", insulin=" + insulin +
                ", bloodsugar=" + bloodsugar +
                '}';
    }
}
