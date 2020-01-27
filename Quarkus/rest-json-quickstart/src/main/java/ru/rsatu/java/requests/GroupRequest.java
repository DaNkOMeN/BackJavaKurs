package ru.rsatu.java.requests;

public class GroupRequest {
    private String nameOfGroup;
    private boolean enable;

    public GroupRequest() {
        nameOfGroup = "default";
        enable = false;
    }

    public String getNameOfGroup() {
        return nameOfGroup;
    }

    public void setNameOfGroup(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public GroupRequest(String nameOfGroup, boolean enable) {
        this.nameOfGroup = nameOfGroup;
        this.enable = enable;
    }
}
