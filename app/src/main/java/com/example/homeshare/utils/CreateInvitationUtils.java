package com.example.homeshare.utils;

public class CreateInvitationUtils {
    static public String validateInputFields(String description, String location, String day, String month, String year, String price, String roommates) {
        if (description.isEmpty()) return "Description field cannot be empty";
        if (location.isEmpty()) return "Location field cannot be empty";
        if (day.isEmpty()) return "Day field cannot be empty";
        if (month.isEmpty()) return "Month field cannot be empty";
        if (year.isEmpty()) return "Year field cannot be empty";
        if (price.isEmpty()) return "Price field cannot be empty";
        if (roommates.isEmpty()) return "Roommates field cannot be empty";
        return "";
    }
}
