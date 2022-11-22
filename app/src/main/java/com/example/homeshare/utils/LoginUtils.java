package com.example.homeshare.utils;

public class LoginUtils {
    static public String validateInputFields(String email, String password) {
        if (email.isEmpty()) return "Email field cannot be empty";
        if (password.isEmpty()) return "Password field cannot be empty";
        return "";
    }
}
