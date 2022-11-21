package com.example.homeshare;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.homeshare.utils.CreateInvitationUtils;

public class CreateInvitationUnitTests {
    @Test
    public void emptyDescriptionReturnsError() {
        String error = CreateInvitationUtils.validateInputFields("","present","present","present","present","present","present");
        assertEquals(error, "Description field cannot be empty");
    }

    @Test
    public void emptyLocationReturnsError() {
        String error = CreateInvitationUtils.validateInputFields("present","","present","present","present","present","present");
        assertEquals(error, "Location field cannot be empty");
    }

    @Test
    public void emptyDayReturnsError() {
        String error = CreateInvitationUtils.validateInputFields("present","present","","present","present","present","present");
        assertEquals(error, "Day field cannot be empty");
    }

    @Test
    public void emptyMonthReturnsError() {
        String error = CreateInvitationUtils.validateInputFields("present","present","present","","present","present","present");
        assertEquals(error, "Month field cannot be empty");
    }

    @Test
    public void emptyYearReturnsError() {
        String error = CreateInvitationUtils.validateInputFields("present","present","present","present","","present","present");
        assertEquals(error, "Year field cannot be empty");
    }

    @Test
    public void emptyPriceReturnsError() {
        String error = CreateInvitationUtils.validateInputFields("present","present","present","present","present","","present");
        assertEquals(error, "Price field cannot be empty");
    }

    @Test
    public void emptyRoommatesReturnsError() {
        String error = CreateInvitationUtils.validateInputFields("present","present","present","present","present","present","");
        assertEquals(error, "Roommates field cannot be empty");
    }

    @Test
    public void filledFieldsReturnsNoError() {
        String error = CreateInvitationUtils.validateInputFields("present","present","present","present","present","present","present");
        assertEquals(error, "");
    }

}
