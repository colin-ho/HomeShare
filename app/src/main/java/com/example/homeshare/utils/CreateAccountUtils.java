package com.example.homeshare.utils;

public class CreateAccountUtils {
    static public String validateInputFields(String fullName, String about, String email, String password, String confirmPassword, String phone) {
        if (fullName.isEmpty()) return "Name field cannot be empty";
        if (about.isEmpty()) return "About field cannot be empty";
        if (email.isEmpty()) return "Email field cannot be empty";
        if (password.isEmpty()) return "Password field cannot be empty";
        if (confirmPassword.isEmpty()) return "Confirm Password field cannot be empty";
        if (phone.isEmpty()) return "Phone field cannot be empty";
        return "";
    }
}
