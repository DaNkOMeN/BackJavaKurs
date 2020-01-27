package ru.rsatu.java.requests;

public class HousingRequest {
    private String numberOfHousing;
    private boolean enable;

    public HousingRequest(String numberOfHousing, boolean enable) {
        this.numberOfHousing = numberOfHousing;
        this.enable = enable;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public HousingRequest() {
    }

    public String getNumberOfHousing() {
        return numberOfHousing;
    }

    public void setNumberOfHousing(String numberOfHousing) {
        this.numberOfHousing = numberOfHousing;
    }
}
