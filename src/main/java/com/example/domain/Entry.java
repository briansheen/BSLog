package com.example.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by bsheen on 6/1/17.
 */

@Entity
@Table(name="entry")

public class Entry {
    private Integer id;
    private LocalDateTime dateTime;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        return id.equals(entry.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                '}';
    }
}
