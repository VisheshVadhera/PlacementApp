package com.vishesh.placement.auth;


import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vishesh.placement.R;
import com.vishesh.placement.dashboard.views.UserNameActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class UserNameActivityTest {

    /**
     * Testing strategy
     * <p>
     * Partition the possible user interactions as follows:
     * <p>
     * 1. Continue button should remain disabled if first name &
     * the last name edit texts are empty.
     * 2. Continue button should remain disabled if either of the
     * first name or last name (but not both) edit texts are non empty.
     * 3. Continue button should be enabled if both the edit texts
     * are non empty.
     */

    private static final String FIRST_NAME = "abc";
    private static final String LAST_NAME = "efg";

    @Rule
    public ActivityTestRule<UserNameActivity> userNameActivityActivityTestRule =
            new ActivityTestRule<>(UserNameActivity.class);

    /**
     * Covers the following:
     * <p>
     * Continue button should remain disabled if first name &
     * the last name edit texts are empty.
     */
    @Test
    public void onBothNamesNotEntered_disableContinueButton() {

        onView(withId(R.id.button_continue))
                .check(matches(not(isEnabled())));
    }

    /**
     * Covers the following:
     * <p>
     * Continue button should remain disabled if either of the
     * first name or last name (but not both) edit texts are non empty.
     */
    @Test
    public void onOnlyOneNameEntered_disableContinueButton() {

        onView(withId(R.id.edit_text_first_name))
                .perform(typeText(FIRST_NAME), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.button_continue))
                .check(matches(not(isEnabled())));
    }

    /**
     * Covers the following:
     * <p>
     * Continue button should be enabled if both the edit texts
     * are non empty.
     */
    @Test
    public void onBothNamesEntered_enableContinueButton() {

        onView(withId(R.id.edit_text_first_name))
                .perform(typeText(FIRST_NAME), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edit_text_last_name))
                .perform(typeText(LAST_NAME), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.button_continue))
                .check(matches(isEnabled()));
    }
}
