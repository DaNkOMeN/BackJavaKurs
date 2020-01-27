package ru.rsatu.java.requests;

import ru.rsatu.java.tables.Groups;

public class UpdateGroupsRequest {
    private String action;
    private Groups groups;

    public UpdateGroupsRequest(String action, Groups groups) {
        this.action = action;
        this.groups = groups;
    }

    public UpdateGroupsRequest() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}