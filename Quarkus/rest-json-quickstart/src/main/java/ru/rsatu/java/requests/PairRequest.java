package ru.rsatu.java.requests;

import java.util.List;

public class PairRequest {

    private int dayOfWeek;
    private int numberOfWeek;
    private int numberOfPair;
    private String userLogin;
    private List<String> groupsName;
    private String housingNumber;
    private String auditoryNumber;
    private String description;


    public PairRequest() {
    }


    public PairRequest(int dayOfWeek, int numberOfWeek, int numberOfPair, String userLogin, List<String> groupsName, String housingNumber, String auditoryNumber, String description) {
        this.dayOfWeek = dayOfWeek;
        this.numberOfWeek = numberOfWeek;
        this.numberOfPair = numberOfPair;
        this.userLogin = userLogin;
        this.groupsName = groupsName;
        this.housingNumber = housingNumber;
        this.auditoryNumber = auditoryNumber;
        this.description = description;
    }


    public String getHousingNumber() {
        return housingNumber;
    }

    public void setHousingNumber(String housingNumber) {
        this.housingNumber = housingNumber;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public List<String> getGroupsName() {
        return groupsName;
    }

    public void setGroupsName(List<String> groupsName) {
        this.groupsName = groupsName;
    }

    public String getAuditoryNumber() {
        return auditoryNumber;
    }

    public void setAuditoryNumber(String auditoryNumber) {
        this.auditoryNumber = auditoryNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
