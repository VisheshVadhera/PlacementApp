package com.vishesh.tpc_stud.auth;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.auth.views.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule =
            new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void testContainsLoginFragment() {
        Fragment loginFragment = loginActivityActivityTestRule
                .getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        assertThat(loginFragment)
                .isNotNull();
    }

    @Test
    public void clickLoginButton_openAccountKitActivity() {

        onView(withId(R.id.button_login)).perform(click());

        onView(withId(R.id.com_accountkit_background)).check(matches(isDisplayed()));
    }
}
