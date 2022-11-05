package com.example.homeshare.Model;

import java.sql.Timestamp;

public class Response {

    private String responseMessage;
    private Timestamp date;
    private Number creatorID;
    private Number invitationID;
    private Boolean isAccepted;
    private Number responderUserID;
    private Number responseID;

    public Response(String responseMessage, Timestamp date, Number creatorID, Number invitationID, Boolean isAccepted, Number responderUserID, Number responseID) {
        this.responseMessage = responseMessage;
        this.date = date;
        this.creatorID = creatorID;
        this.invitationID = invitationID;
        this.isAccepted = isAccepted;
        this.responderUserID = responderUserID;
        this.responseID = responseID;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Number getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(Number creatorID) {
        this.creatorID = creatorID;
    }

    public Number getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(Number invitationID) {
        this.invitationID = invitationID;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Number getResponderUserID() {
        return responderUserID;
    }

    public void setResponderUserID(Number responderUserID) {
        this.responderUserID = responderUserID;
    }

    public Number getResponseID() {
        return responseID;
    }

    public void setResponseID(Number responseID) {
        this.responseID = responseID;
    }
}
