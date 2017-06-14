package com.example.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bsheen on 6/2/17.
 */

@Entity
@Table(name="user")
public class User {
    private String username;
    private Boolean enabled;
    private Integer isr;
    private Integer icr;
    private String insulinType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Entry> entries;

    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getIsr() {
        return isr;
    }

    public void setIsr(Integer isr) {
        this.isr = isr;
    }

    public Integer getIcr() {
        return icr;
    }

    public void setIcr(Integer icr) {
        this.icr = icr;
    }

    public String getInsulinType() {
        return insulinType;
    }

    public void setInsulinType(String insulinType) {
        this.insulinType = insulinType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", enabled=" + enabled +
                ", isr=" + isr +
                ", icr=" + icr +
                ", insulinType='" + insulinType + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", entries=" + entries +
                '}';
    }
}


