package ru.rsatu.java.requests;

public class AuditoryRequest {
    private String number;
    private String housingNumber;
    private boolean isComputer;
    private boolean isProjector;
    private boolean available;


    public AuditoryRequest(String number, String housingNumber, boolean isComputer, boolean isProjector, boolean available) {
        this.number = number;
        this.housingNumber = housingNumber;
        this.isComputer = isComputer;
        this.isProjector = isProjector;
        this.available = available;
    }

    public AuditoryRequest() {
    }

    public String getNumber() {
        return number;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
