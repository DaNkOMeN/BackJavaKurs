package ru.rsatu.java.requests;

import ru.rsatu.java.tables.Housing;

public class UpdateHousingRequest {
    private String action;
    private Housing corp;

    public UpdateHousingRequest(String action, Housing corp) {
        this.action = action;
        this.corp = corp;
    }


    public UpdateHousingRequest() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Housing getCorp() {
        return corp;
    }

    public void setCorp(Housing corp) {
        this.corp = corp;
    }
}
