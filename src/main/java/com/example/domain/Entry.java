package com.example.domain;

import java.time.LocalDateTime;

/**
 * Created by bsheen on 6/1/17.
 */
public class Entry {

    private long id;
    private int bloodsugar;
    private int carbs;
    private double insulin;
    private LocalDateTime time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBloodsugar() {
        return bloodsugar;
    }

    public void setBloodsugar(int bloodsugar) {
        this.bloodsugar = bloodsugar;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public double getInsulin() {
        return insulin;
    }

    public void setInsulin(double insulin) {
        this.insulin = insulin;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (id != entry.id) return false;
        if (bloodsugar != entry.bloodsugar) return false;
        if (carbs != entry.carbs) return false;
        if (Double.compare(entry.insulin, insulin) != 0) return false;
        return time != null ? time.equals(entry.time) : entry.time == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + bloodsugar;
        result = 31 * result + carbs;
        temp = Double.doubleToLongBits(insulin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", bloodsugar=" + bloodsugar +
                ", carbs=" + carbs +
                ", insulin=" + insulin +
                ", time=" + time +
                '}';
    }
}
