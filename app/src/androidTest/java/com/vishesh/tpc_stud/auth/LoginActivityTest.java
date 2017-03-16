package com.vishesh.tpc_stud.auth;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.facebook.accountkit.ui.AccountKitActivity;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.auth.views.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    /**
     * Testing Strategy:
     * <p>
     * <p>
     * For login flow, partition the state space as follows:
     * <p>
     * Successful login and no cancellation by the user,
     * Successful login and cancellation by the user,
     * Unsuccessful login.
     */

    @Rule
    public IntentsTestRule<LoginActivity> loginActivityIntentsTestRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void testContainsLoginFragment() {
        Fragment loginFragment = loginActivityIntentsTestRule
                .getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        assertThat(loginFragment)
                .isNotNull();
    }

    /**
     * Covers the following case:
     * Successful login and no cancellation by the user
     */
    @Test
    public void clickLoginButton_openAccountKitActivity() {

        onView(withId(R.id.button_login)).perform(click());

        onView(withId(R.id.com_accountkit_background)).check(matches(isDisplayed()));

        ActivityResult activityResult = createAccountKitActivitySuccessfulLoginResultStub();

        intending(hasExtraWithKey(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION))
                .respondWith(activityResult);


    }

    private ActivityResult createAccountKitActivitySuccessfulLoginResultStub() {
        Intent intent = new Intent();
//        AccountKitLoginResult accountKitLoginResult =
//        intent.putExtra()
        return new ActivityResult(Activity.RESULT_OK, intent);
    }

}
