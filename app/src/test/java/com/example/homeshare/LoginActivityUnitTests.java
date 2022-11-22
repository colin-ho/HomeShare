package com.example.homeshare;

import static org.junit.Assert.assertEquals;

import com.example.homeshare.utils.LoginUtils;

import org.junit.Test;

public class LoginActivityUnitTests {

    @Test
    public void emptyEmailReturnsError() {
        String error = LoginUtils.validateInputFields("", "present");
        assertEquals(error, "Email field cannot be empty");
    }

    @Test
    public void emptyPasswordReturnsError() {
        String error = LoginUtils.validateInputFields("present", "");
        assertEquals(error, "Password field cannot be empty");
    }

    @Test
    public void filledFieldsReturnsNoError() {
        String error = LoginUtils.validateInputFields("present", "present");
        assertEquals(error, "");
    }
}
