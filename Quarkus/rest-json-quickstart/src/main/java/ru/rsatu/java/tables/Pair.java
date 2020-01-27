package ru.rsatu.java.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pair")
public class Pair extends PanacheEntity implements Serializable {

    @Column(name = "dayOfWeek", nullable = false)
    private int dayOfWeek;

    @Column(name = "numberOfWeek", nullable = false)
    private int numberOfWeek;

    @Column(name = "numberOfPair", nullable = false)
    private int numberOfPair;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonbTransient
    private User user;

    @ManyToMany
    @JsonbTransient
    private List<Groups> groups;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "auditory_id", nullable = false)
    @JsonbTransient
    private Auditory auditory;

    @Column(name = "description", nullable = false)
    private String description;

    public Pair(int dayOfWeek, int numberOfWeek, int numberOfPair, User teacher, List<Groups> groups, Auditory auditory, String description) {
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
        this.numberOfPair = numberOfPair;
        this.user = teacher;
        this.groups = groups;
        this.auditory = auditory;
        this.description = description;
    }

    public Pair() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getNumberOfWeek() {
        return numberOfWeek;
    }

    public void setNumberOfWeek(int numberOfWeek) {
        this.numberOfWeek = numberOfWeek;
    }

    public int getNumberOfPair() {
        return numberOfPair;
    }

    public void setNumberOfPair(int numberOfPair) {
        this.numberOfPair = numberOfPair;
    }

    public User getTeacher() {
        return user;
    }

    public void setTeacher(User teacher) {
        this.user = teacher;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public Auditory getAuditory() {
        return auditory;
    }

    public void setAuditory(Auditory auditory) {
        this.auditory = auditory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
