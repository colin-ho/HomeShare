package com.example.homeshare;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.homeshare.testUtils.ToastMatcher;

import org.junit.Rule;
import org.junit.Test;

public class CreateAccountActivityTest {
    @Rule
    public ActivityScenarioRule<CreateAccountActivity> activityScenarioRule = new ActivityScenarioRule<CreateAccountActivity>(CreateAccountActivity.class);

    @Test
    public void TestCreateAccountWithoutName() throws InterruptedException {
        fillall();
        onView(withId(R.id.createAccountFullName)).perform(ViewActions.replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.createAccountButton)).perform(ViewActions.click());
        onView(withText("Name field cannot be empty")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void TestCreateAccountWithoutEmail() throws InterruptedException {
        fillall();
        onView(withId(R.id.createAccountEmailAddress)).perform(ViewActions.replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.createAccountButton)).perform(ViewActions.click());
        onView(withText("Email field cannot be empty")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void TestCreateAccountWithoutAbout() throws InterruptedException {
        fillall();
        onView(withId(R.id.createAccountAbout)).perform(ViewActions.replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.createAccountButton)).perform(ViewActions.click());
        onView(withText("About field cannot be empty")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }
//
    @Test
    public void TestCreateAccountWithoutPassword() throws InterruptedException {
        fillall();
        onView(withId(R.id.createAccountPassword)).perform(ViewActions.replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.createAccountButton)).perform(ViewActions.click());
        onView(withText("Password field cannot be empty")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }
//
    @Test
    public void TestCreateAccountWithoutConfirmPassword() throws InterruptedException {
        fillall();
        onView(withId(R.id.createAccountConfirmPassword)).perform(ViewActions.replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.createAccountButton)).perform(ViewActions.click());
        onView(withText("Confirm Password field cannot be empty")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    @Test
    public void TestCreateAccountWithoutPhone() throws InterruptedException {
        fillall();
        onView(withId(R.id.createAccountPhone)).perform(ViewActions.replaceText(""),closeSoftKeyboard());
        onView(withId(R.id.createAccountButton)).perform(ViewActions.click());
        onView(withText("Phone field cannot be empty")).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }

    public void fillall() {
        onView(withId(R.id.createAccountEmailAddress)).perform(ViewActions.replaceText("test@gmail.com"));
        onView(withId(R.id.createAccountPassword)).perform(ViewActions.replaceText("password"));
        onView(withId(R.id.createAccountConfirmPassword)).perform(ViewActions.replaceText("password"));
        onView(withId(R.id.createAccountAbout)).perform(ViewActions.replaceText("about"));
        onView(withId(R.id.createAccountFullName)).perform(ViewActions.replaceText("name"));
        onView(withId(R.id.createAccountPhone)).perform(ViewActions.replaceText("1234567890"));
    }
}
