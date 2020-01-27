package ru.rsatu.java.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Groups extends PanacheEntity implements Serializable {

    @Column(name = "nameOfGroup", unique = true, nullable = false, length = 25)
    private String nameOfGroup;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    @JsonbTransient
    private List<Pair> pair = new ArrayList<>();

    @Column(name = "enable", nullable = false)
    private boolean enable;

    public Groups(String nameOfGroup, boolean enable) {
        this.nameOfGroup = nameOfGroup;
        this.enable = enable;
        pair = new ArrayList<>();
    }


    public Groups() {
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Pair> getPairs() {
        return pair;
    }

    public void setPairs(List<Pair> pairs) {
        this.pair = pairs;
    }

    public String getNameOfGroup() {
        return nameOfGroup;
    }

    public void setNameOfGroup(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
    }
}
