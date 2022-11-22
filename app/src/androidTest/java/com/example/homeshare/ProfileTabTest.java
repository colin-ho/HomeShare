package com.example.homeshare;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
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
import com.example.homeshare.ui.profile.ProfileFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProfileTabTest {
    @Rule
    public ActivityScenarioRule<HomeActivity> activityScenarioRule = new ActivityScenarioRule<HomeActivity>(HomeActivity.class);
    @Before
    public void TestSwitchToProfileScreen(){
        onView(withId(R.id.navigation_profile)).perform(ViewActions.click());
        onView(withId(R.id.fragment_profile)).check(matches(isDisplayed()));
    }
    @Test
    public void Test1() throws InterruptedException {
        onView(withId(R.id.personName)).perform(clearText(),typeText("TestName"),closeSoftKeyboard());
        Thread.sleep(2000);
        onView(withId(R.id.updateProfile)).perform(scrollTo()).perform(ViewActions.click());
        Thread.sleep(2000);
        onView(withId(R.id.personName)).check(matches(withText("TestName")));
    }
    @Test
    public void Test2()throws InterruptedException {
        onView(withId(R.id.phoneNumber)).perform(clearText(),typeText("12345678"),closeSoftKeyboard());
        Thread.sleep(2000);
        onView(withId(R.id.updateProfile)).perform(scrollTo()).perform(ViewActions.click());
        Thread.sleep(2000);
        onView(withId(R.id.phoneNumber)).check(matches(withText("12345678")));
    }
    @Test
    public void Test3()throws InterruptedException {

        onView(withId(R.id.description)).perform(clearText(),typeText("TestDesc"),closeSoftKeyboard());
        Thread.sleep(2000);
        onView(withId(R.id.updateProfile)).perform(scrollTo()).perform(ViewActions.click());
        Thread.sleep(2000);
        onView(withId(R.id.description)).check(matches(withText("TestDesc")));

    }
    @Test
    public void Test4(){
       onView(withId(R.id.logoutProfileButton)).perform(scrollTo()).perform(ViewActions.click());
       onView(withId(R.id.layout_login)).check(matches(isDisplayed()));
    }




}
