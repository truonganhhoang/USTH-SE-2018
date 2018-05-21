package com.example.Big4.se;

import android.support.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class CheckNewLocationInfor {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testWithTokyo(){
        onView(withId(R.id.text_location))
                .perform(typeText("Tokyo"));
        onView(withId(R.id.button_location))
                .perform(click());
        onView(withId(R.id.temp_text))
                .check(matches(isDisplayed()));
        onView(withId(R.id.press_text))
                .check(matches(isDisplayed()));
        onView(withId(R.id.humid_text))
                .check(matches(isDisplayed()));
        onView(withId(R.id.desc_text))
                .check(matches(isDisplayed()));
        onView(withId(R.id.today))
                .check(matches(isDisplayed()));

    }

    @After
    public void tearDown() throws Exception {
    }
}
