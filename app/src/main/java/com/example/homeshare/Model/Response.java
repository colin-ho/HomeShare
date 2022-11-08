package com.example.homeshare.Model;

import java.sql.Timestamp;
import java.util.Date;

public class Response {

    private String responseMessage;
    private Date date;
    private String invitationID;
    private String status;
    private String responderUserID;
    private String responderName;
    private String invitationCreatorUserID;
    private String responseID;

    public Response(){

    }
    public Response(String responseMessage, Date date, String invitationID, String status, String responderUserID, String responderName, String invitationCreatorUserID) {
        this.responseMessage = responseMessage;
        this.date = date;
        this.invitationID = invitationID;
        this.status = status;
        this.responderUserID = responderUserID;
        this.responderName = responderName;
        this.invitationCreatorUserID = invitationCreatorUserID;
    }

    public String getResponseID() {
        return responseID;
    }

    public void setResponseID(String responseID) {
        this.responseID = responseID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponderUserID() {
        return responderUserID;
    }

    public void setResponderUserID(String responderUserID) {
        this.responderUserID = responderUserID;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseMessage='" + responseMessage + '\'' +
                ", date=" + date +
                ", invitationID='" + invitationID + '\'' +
                ", status='" + status + '\'' +
                ", responderUserID='" + responderUserID + '\'' +
                ", responderName='" + responderName + '\'' +
                ", invitationCreatorUserID='" + invitationCreatorUserID + '\'' +
                ", responseID='" + responseID + '\'' +
                '}';
    }
}
