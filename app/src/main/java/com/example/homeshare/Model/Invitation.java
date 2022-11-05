package com.example.homeshare.Model;

import java.sql.Timestamp;

public class Invitation {

    private Timestamp deadline;
    private Number creatorUserID;
    private Number invitationID;
    private String location;
    private Number noOfBeds;
    private Number price;
    private String open;
    private String[] utilities;
    private String description;

    public Invitation(Timestamp deadline, Number creatorUserID, Number invitationID, String description, String location, Number noOfBeds, Number price, String open, String[] utilities) {
        this.deadline = deadline;
        this.creatorUserID = creatorUserID;
        this.invitationID = invitationID;
        this.location = location;
        this.noOfBeds = noOfBeds;
        this.price = price;
        this.open = open;
        this.utilities = utilities;
        this.description  = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public Number getCreatorUserID() {
        return creatorUserID;
    }

    public void setCreatorUserID(Number creatorUserID) {
        this.creatorUserID = creatorUserID;
    }

    public Number getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(Number invitationID) {
        this.invitationID = invitationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Number getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(Number noOfBeds) {
        this.noOfBeds = noOfBeds;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String[] getUtilities() {
        return utilities;
    }

    public void setUtilities(String[] utilities) {
        this.utilities = utilities;
    }
}
