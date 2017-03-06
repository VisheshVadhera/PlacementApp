package com.vishesh.tpc_stud.auth;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.vishesh.tpc_stud.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginViewTest {

    @Test
    public void clickLoginButton_openAccountKitActivity() {

        onView(withId(R.id.button_login)).perform(click());

        onView(withId(R.id.com_accountkit_title)).check(matches(isDisplayed()));
    }
}
