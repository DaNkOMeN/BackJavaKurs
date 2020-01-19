package ru.rsatu.java.beans;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "auditory")
public class Auditory extends PanacheEntity {
    private String housing; //корпус
    private String number; //номер аудитории
    private boolean projector;// есть ли прожектор
    private boolean computer; // есть ли компьютеры
    private String other; //примечания

    public Auditory(String housing, String number, boolean projector, boolean computer, String other) {
        this.housing = housing;
        this.number = number;
        this.projector = projector;
        this.computer = computer;
        this.other = other;
    }

    public Auditory() {
        this.housing = "default";
        this.number = "default";
        this.projector = false;
        this.computer = false;
        this.other = "default";
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public boolean isComputer() {
        return computer;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
