package com.example.homeshare.Model;

import java.sql.Timestamp;
import java.util.Map;

public class Invitation {

    private String name;
    private String creatorUserID;
    private String description;
    private String location;
    private String day;
    private String month;
    private String year;
    private String price;
    private int roommates;
    private Map<String, Object> utilities;
    private String invitationID;

    public Invitation() {
    }

    public Invitation(String name, String creatorUserID, String description, String location, String day, String month, String year, String price, int roommates, Map<String, Object> utilities) {
        this.name = name;
        this.creatorUserID = creatorUserID;
        this.description = description;
        this.location = location;
        this.day = day;
        this.month = month;
        this.year = year;
        this.price = price;
        this.roommates = roommates;
        this.utilities = utilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatorUserID() {
        return creatorUserID;
    }

    public void setCreatorUserID(String creatorUserID) {
        this.creatorUserID = creatorUserID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getRoommates() {
        return roommates;
    }

    public void setRoommates(int roommates) {
        this.roommates = roommates;
    }

    public Map<String, Object> getUtilities() {
        return utilities;
    }

    public void setUtilities(Map<String, Object> utilities) {
        this.utilities = utilities;
    }

    public String getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(String invitationID) {
        this.invitationID = invitationID;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "name='" + name + '\'' +
                ", creatorUserID='" + creatorUserID + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", day='" + day + '\'' +
                ", month='" + month + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", roommates=" + roommates +
                ", utilities=" + utilities +
                ", invitationID='" + invitationID + '\'' +
                '}';
    }
}
