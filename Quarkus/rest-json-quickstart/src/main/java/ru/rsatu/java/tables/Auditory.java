package ru.rsatu.java.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "auditory")
public class Auditory extends PanacheEntity implements Serializable {

    @Column(name = "number", nullable = false)
    private String number;

    @ManyToOne(optional = false)
    @JsonbTransient
    private Housing housing;

    @Column(name = "isComputer", nullable = false)
    private boolean isComputer;

    @Column(name = "isProjector", nullable = false)
    private boolean isProjector;

    @Column(name = "enable", nullable = false)
    private boolean enable;

    @OneToMany(mappedBy = "auditory" , cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    private Set<Pair> pairsOfTeacher;

    public Auditory(String number, Housing housing, boolean isComputer, boolean isProjector, boolean enable) {
        this.number = number;
        this.housing = housing;
        this.isComputer = isComputer;
        this.isProjector = isProjector;
        this.enable = enable;
        pairsOfTeacher = new HashSet<>();
    }


    public Auditory() {
    }

    public Set<Pair> getPairsOfTeacher() {
        return pairsOfTeacher;
    }

    public void setPairsOfTeacher(Set<Pair> pairsOfTeacher) {
        this.pairsOfTeacher = pairsOfTeacher;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Housing getHousing() {
        return housing;
    }

    public void setHousing(Housing housing) {
        this.housing = housing;
    }


    public boolean isComputer() {
        return isComputer;
    }

    public void setComputer(boolean computer) {
        isComputer = computer;
    }

    public boolean isProjector() {
        return isProjector;
    }

    public void setProjector(boolean projector) {
        isProjector = projector;
    }


    public boolean isAvailable() {
        return enable;
    }

    public void setAvailable(boolean available) {
        this.enable = available;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Auditory other = (Auditory)obj;
        return Objects.equals(this.number, other.number);
    }
}
