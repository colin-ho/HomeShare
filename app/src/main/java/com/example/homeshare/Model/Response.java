package com.example.homeshare.Model;

import java.sql.Timestamp;

public class Response {

    private String responseMessage;
    private Timestamp date;
    private String invitationID;
    private Boolean isAccepted;
    private String responderUserID;
    private String responderName;
    private String invitationCreatorUserID;

    public Response(){

    }
    public Response(String responseMessage, Timestamp date, String invitationID, Boolean isAccepted, String responderUserID, String responderName,String invitationCreatorUserID) {
        this.responseMessage = responseMessage;
        this.date = date;
        this.invitationID = invitationID;
        this.isAccepted = isAccepted;
        this.responderUserID = responderUserID;
        this.responderName = responderName;
        this.invitationCreatorUserID = invitationCreatorUserID;
    }

    public String getInvitationCreatorUserID() {
        return invitationCreatorUserID;
    }

    public void setInvitationCreatorUserID(String invitationCreatorUserID) {
        this.invitationCreatorUserID = invitationCreatorUserID;
    }

    public String getResponderName() {
        return responderName;
    }

    public void setResponderName(String responderName) {
        this.responderName = responderName;
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

    public String getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(String invitationID) {
        this.invitationID = invitationID;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public String getResponderUserID() {
        return responderUserID;
    }

    public void setResponderUserID(String responderUserID) {
        this.responderUserID = responderUserID;
    }
}
