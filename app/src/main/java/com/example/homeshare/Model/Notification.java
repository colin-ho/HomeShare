package com.example.homeshare.Model;

import java.util.Date;

public class Notification {
    public String getStatus() {
        return status;
    }

    public String getResponderUserID() {
        return responderUserID;
    }

    public String getResponderName() {
        return responderName;
    }

    public String getInvitationCreatorUserID() {
        return invitationCreatorUserID;
    }

    public String getResponseID() {
        return responseID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResponderUserID(String responderUserID) {
        this.responderUserID = responderUserID;
    }

    public void setResponderName(String responderName) {
        this.responderName = responderName;
    }

    public void setInvitationCreatorUserID(String invitationCreatorUserID) {
        this.invitationCreatorUserID = invitationCreatorUserID;
    }

    public void setResponseID(String responseID) {
        this.responseID = responseID;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "status='" + status + '\'' +
                ", responderUserID='" + responderUserID + '\'' +
                ", responderName='" + responderName + '\'' +
                ", invitationCreatorUserID='" + invitationCreatorUserID + '\'' +
                ", responseID='" + responseID + '\'' +
                '}';
    }

    public Notification(String responseMessage, Date date, String invitationID, String status, String responderUserID, String responderName, String invitationCreatorUserID) {
        this.status = status;
        this.responderUserID = responderUserID;
        this.responderName = responderName;
        this.invitationCreatorUserID = invitationCreatorUserID;
    }

    public Notification() {}

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(String invitationID) {
        this.invitationID = invitationID;
    }

    private String responseMessage;
    private Date date;
    private String invitationID;
    private String status;
    private String responderUserID;
    private String responderName;
    private String invitationCreatorUserID;
    private String responseID;
}
