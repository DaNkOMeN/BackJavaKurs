package ru.rsatu.java.requests;

public class AuditoryResponse {

    private int id;
    private String number;
    private String housingNumber;
    private boolean isComputer;
    private boolean isProjector;
    private boolean enable;

    public AuditoryResponse(int id, String number, String housingNumber, boolean isComputer, boolean isProjector, boolean enable) {
        this.id = id;
        this.number = number;
        this.housingNumber = housingNumber;
        this.isComputer = isComputer;
        this.isProjector = isProjector;
        this.enable = enable;
    }

    public AuditoryResponse() {
    }

    public String getNumber() {
        return number;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHousingNumber() {
        return housingNumber;
    }

    public void setHousingNumber(String housingNumber) {
        this.housingNumber = housingNumber;
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

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
