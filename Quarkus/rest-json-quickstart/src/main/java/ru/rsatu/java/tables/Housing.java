package ru.rsatu.java.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "housing")
public class Housing extends PanacheEntity implements Serializable{

    @Column(name = "numberOfHousing", unique = true, nullable = false, length = 25)
    private String numberOfHousing;

    @OneToMany(mappedBy = "housing",cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    private Set<Auditory> housingsAuditory ;

    @Column(name = "enable", nullable = false)
    private boolean enable;

    public Housing(String numberOfHousing, boolean enable) {
        this.numberOfHousing = numberOfHousing;
        this.enable = enable;

    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Housing() {
        numberOfHousing = "default";
       // enable = false;
    }

    public Housing(String numberOfHousing) {
        this.numberOfHousing = numberOfHousing;
        // enable = false;
    }

    public String getNumberOfHousing() {
        return numberOfHousing;
    }

    public void setNumberOfHousing(String numberOfHousing) {
        this.numberOfHousing = numberOfHousing;
    }

    public Set<Auditory> getHousingsAuditory() {
        return housingsAuditory;
    }

    public void setHousingsAuditory(Set<Auditory> housingsAuditory) {
        this.housingsAuditory = housingsAuditory;
    }

    public void addHousingsAuditory(Auditory auditory){this.housingsAuditory.add(auditory);}

    public boolean hasAuditory(String auditoryNumber){
        for (Auditory au : housingsAuditory){
            if (au.getNumber() == auditoryNumber){
                return true;
            }
        }
        return false;
    }
}
