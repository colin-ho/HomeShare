package com.example.homeshare;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.homeshare.utils.CreateAccountUtils;
import com.example.homeshare.utils.CreateInvitationUtils;

public class CreateAccountActivityUnitTests {

    @Test
    public void emptyNameReturnsError() {
        String error = CreateAccountUtils.validateInputFields("", "present", "present","present", "present", "present");
        assertEquals(error, "Name field cannot be empty");
    }

    @Test
    public void emptyAboutReturnsError() {
        String error = CreateAccountUtils.validateInputFields("present", "", "present","present", "present", "present");
        assertEquals(error, "About field cannot be empty");
    }

    @Test
    public void emptyEmailReturnsError() {
        String error = CreateAccountUtils.validateInputFields("present", "present", "","present", "present", "present");
        assertEquals(error, "Email field cannot be empty");
    }

    @Test
    public void emptyPasswordReturnsError() {
        String error = CreateAccountUtils.validateInputFields("present", "present", "present","", "present", "present");
        assertEquals(error, "Password field cannot be empty");
    }

    @Test
    public void emptyConfirmPasswordReturnsError() {
        String error = CreateAccountUtils.validateInputFields("present", "present", "present","present", "", "present");
        assertEquals(error, "Confirm Password field cannot be empty");
    }

    @Test
    public void emptyPhoneReturnsError() {
        String error = CreateAccountUtils.validateInputFields("present", "present", "present","present", "present", "");
        assertEquals(error, "Phone field cannot be empty");
    }

    @Test
    public void filledFieldsReturnsNoError() {
        String error = CreateAccountUtils.validateInputFields("present", "present", "present","present", "present", "present");
        assertEquals(error, "");
    }

}

