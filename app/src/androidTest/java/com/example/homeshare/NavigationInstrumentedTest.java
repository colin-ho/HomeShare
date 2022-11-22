package com.example.homeshare;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class NavigationInstrumentedTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityScenarioRule = new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    @Test
    public void TestSwitchToCreateInvitationScreen(){
        onView(withId(R.id.navigation_createInvitation)).perform(ViewActions.click());
        onView(withId(R.id.fragment_create_invitation)).check(matches(isDisplayed()));
    }

    @Test
    public void TestSwitchToProfileScreen(){
        onView(withId(R.id.navigation_profile)).perform(ViewActions.click());
        onView(withId(R.id.fragment_profile)).check(matches(isDisplayed()));
    }

    @Test
    public void TestSwitchToHomeScreen(){
        onView(withId(R.id.navigation_home)).perform(ViewActions.click());
        onView(withId(R.id.fragment_home)).check(matches(isDisplayed()));
    }
}
