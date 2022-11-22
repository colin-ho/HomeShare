package com.example.homeshare;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import static org.hamcrest.Matchers.not;

import com.example.homeshare.testUtils.ToastMatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class CreateInvitationTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityScenarioRule = new ActivityScenarioRule<HomeActivity>(HomeActivity.class);

    @Before
    public void TestSwitchToCreateInvitationScreen(){
        onView(withId(R.id.navigation_createInvitation)).perform(ViewActions.click());
        onView(withId(R.id.fragment_create_invitation)).check(matches(isDisplayed()));
    }

    @Test
    public void TestCreateInvitationWithoutFields(){
        onView(withId(R.id.createInvitationButton)).perform(ViewActions.click());
        onView(withText("Description field cannot be empty")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void TestCreateInvitationWithoutDescription(){
        fillAll();
        onView(withId(R.id.createInvitationDescription)).perform(ViewActions.replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.createInvitationButton)).perform(ViewActions.click());
        onView(withText("Description field cannot be empty")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    public void fillAll(){
        onView(withId(R.id.createInvitationLocation)).perform(ViewActions.replaceText("testLocation"));
        onView(withId(R.id.createInvitationDescription)).perform(ViewActions.replaceText("testDescription"));
        onView(withId(R.id.createInvitationDay)).perform(ViewActions.replaceText("testDay"));
        onView(withId(R.id.createInvitationMonth)).perform(ViewActions.replaceText("testMonth"));
        onView(withId(R.id.createInvitationYear)).perform(ViewActions.replaceText("testYear"));
        onView(withId(R.id.createInvitationRoommates)).perform(ViewActions.replaceText("1"));
        onView(withId(R.id.createInvitationPrice)).perform(ViewActions.replaceText("testPrice"));
    }

}
