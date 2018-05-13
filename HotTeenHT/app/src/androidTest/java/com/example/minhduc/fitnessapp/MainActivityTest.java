package com.example.minhduc.fitnessapp;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static java.util.regex.Pattern.matches;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule public final ActivityRule<MainActivity> main = new ActivityRule<>(MainActivity.class);

    @Test
    public void ShouldBeAbleTolaunchMainScreen(){
        onView(withText("Ready to start?")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void WillClickLetsGOOpenNextActivity() {
        //Click the lets o button
        onView(withId(R.id.customWorkoutButton)).perform(click());
        //Click the add button
        onView(withId(R.id.addWorkoutButton)).perform(click());
        //Type the button
        onView(withId(R.id.workoutNameEditText)).perform(typeText("a"),closeSoftKeyboard());
        //Save the excercise
        onView(withId(R.id.confirmWorkoutNameButton)).perform((click()));
        //click finish
        onView(withId(R.id.buttonFinish)).perform(click());
        //check if its appear on menu
        onView(withText("a")).check(ViewAssertions.matches(isDisplayed()));

    }

    @Test
    public void ReNameAWorkOut(){
       //Click the lets o button
        onView(withId(R.id.customWorkoutButton)).perform(click());
        //Hold the "a" workout line
        onView(withText("a")).perform(longClick());
        //click Rename
        onView(withText("Rename")).perform(click());
        // Rename the workout
        onView(withText("Name Here")).perform(replaceText("UI TEST"));
        // Click OK
        onView(withText("OK")).perform(click());
        // Check if its rename it or not
        onView(withText("UI TEST")).check(ViewAssertions.matches(isDisplayed()));

    }

    @Test
    public void DeleteAWorkOut(){
        //Click the lets o button
        onView(withId(R.id.customWorkoutButton)).perform(click());
        //Hold the "UI TEST" workout line
        onView(withText("UI TEST")).perform(longClick());
        // Click Delete
        onView(withText("Delete")).perform(click());
        // click Ok
        onView(withText("OK")).perform(click());
        // check if its not appear on menu
        onView(withText("UI TEST")).check(ViewAssertions.doesNotExist());
    }





}
