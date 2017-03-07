package com.vishesh.tpc_stud.auth;


import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.auth.views.UserNameActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class UserNameActivityTest {

    @Rule
    public ActivityTestRule<UserNameActivity> userNameActivityActivityTestRule =
            new ActivityTestRule<UserNameActivity>(UserNameActivity.class);


    @Test
    public void onFirstNameLastName() {

        String firstName = "abc";
        String lastName = "efg";

        onView(withId(R.id.edit_text_first_name))
                .perform(typeText(firstName), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edit_text_last_name))
                .perform(typeText(lastName), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.button_continue))
                .check(matches(isEnabled()))
                .perform(click());

    }
}
