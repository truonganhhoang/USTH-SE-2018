package com.example.minhduc.fitnessapp;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static java.util.regex.Pattern.matches;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.StringEndsWith.endsWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule public final ActivityRule<MainActivity> main = new ActivityRule<>(MainActivity.class);

    @Test
    public void ShouldBeAbleToLaunchMainScreen(){
        onView(withText("Ready to start?")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testListViewBehavior() {
        // Click the lets go button
        onView(withId(R.id.customWorkoutButton)).perform(click());
        // Click the add button
        onView(withId(R.id.addWorkoutButton)).perform(click());
        // Type the name
        onView(allOf(withClassName(endsWith("EditText")), withText(is("")))).perform(replaceText("a"));
        // Save the exercise
        onView(withId(R.id.confirmWorkoutNameButton)).perform((click()));
        // Click finish
        onView(withId(R.id.buttonFinish)).perform(click());
        // Check if its appear on menu
        onView(withText("a")).check(ViewAssertions.matches(isDisplayed()));
        // Hold the "a" workout line
        onView(withText("a")).perform(longClick());
        // Click Rename
        onView(withText("Rename")).perform(click());
        // Rename the workout
        onView(allOf(withClassName(endsWith("EditText")), withText(is("")))).perform(replaceText("b"));
        // Click OK
        onView(withText("OK")).perform(click());
        // Check if its rename it or not
        onView(withText("b")).check(ViewAssertions.matches(isDisplayed()));
        // Hold the "UI TEST" workout line
        onView(withText("b")).perform(longClick());
        // Click Delete
        onView(withText("Delete")).perform(click());
        // click Ok
        onView(withText("OK")).perform(click());
        // check if its not appear on menu
        onView(withText("b")).check(ViewAssertions.doesNotExist());
    }
}
