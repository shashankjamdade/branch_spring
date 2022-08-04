package com.fsfb.branchregister.branchregister.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RequestStatus {
    @JsonProperty("req_raised")
    REQ_RAISED("REQ_RAISED"),
    @JsonProperty("bom_approved")
    BOM_APPROVED("BOM_APPROVED"),
    @JsonProperty("bom_rejected")
    BOM_REJECTED("BOM_REJECTED"),
    @JsonProperty("zh_approved")
    ZH_APPROVED("ZH_APPROVED"),
    @JsonProperty("zh_rejected")
    ZH_REJECTED("ZH_REJECTED"),
    @JsonProperty("reciever_accepted")
    RECIEVER_ACCEPTED("RECIEVER_ACCEPTED"),
    @JsonProperty("reciever_rejected")
    RECIEVER_REJECTED("RECIEVER_REJECTED");

    private String requestStatus;

    RequestStatus(String status) {
        this.requestStatus = status;
    }

    public static RequestStatus recreateEnum(String value) {
        RequestStatus enumVal = null;
        if (value != null) {
            if (value.equalsIgnoreCase("REQ_RAISED"))
                enumVal = RequestStatus.REQ_RAISED;
            else if (value.equalsIgnoreCase("BOM_APPROVED"))
                enumVal = RequestStatus.BOM_APPROVED;
            else if (value.equalsIgnoreCase("BOM_REJECTED"))
                enumVal = RequestStatus.BOM_REJECTED;
            else if (value.equalsIgnoreCase("ZH_APPROVED"))
                enumVal = RequestStatus.ZH_APPROVED;
            else if (value.equalsIgnoreCase("ZH_REJECTED"))
                enumVal = RequestStatus.ZH_REJECTED;
            else if (value.equalsIgnoreCase("RECIEVER_ACCEPTED"))
                enumVal = RequestStatus.RECIEVER_ACCEPTED;
            else if (value.equalsIgnoreCase("RECIEVER_REJECTED"))
                enumVal = RequestStatus.RECIEVER_REJECTED;

        }
        return enumVal;
    }

    public String recreateString(){
        return requestStatus;
    }

}
