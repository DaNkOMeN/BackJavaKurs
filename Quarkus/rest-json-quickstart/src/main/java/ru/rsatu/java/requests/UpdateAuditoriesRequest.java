package ru.rsatu.java.requests;

public class UpdateAuditoriesRequest {
    private String action;
    private int housingId;
    private AuditoryResponse auditory;


    public UpdateAuditoriesRequest(String action, int housingId, AuditoryResponse auditory) {
        this.action = action;
        this.housingId = housingId;
        this.auditory = auditory;
    }

    public int getHousingId() {
        return housingId;
    }

    public void setHousingId(int housingId) {
        this.housingId = housingId;
    }

    public UpdateAuditoriesRequest() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public AuditoryResponse getAuditory() {
        return auditory;
    }

    public void setAuditory(AuditoryResponse auditory) {
        this.auditory = auditory;
    }
}
