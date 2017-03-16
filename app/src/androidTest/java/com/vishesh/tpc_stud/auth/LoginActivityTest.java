package com.vishesh.tpc_stud.auth;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.auth.views.LoginActivity;
import com.vishesh.tpc_stud.core.TpcStudApplication;
import com.vishesh.tpc_stud.core.dagger.TestAppComponent;
import com.vishesh.tpc_stud.dashboard.views.DashboardActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


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

    @Inject
    OkHttpClient okHttpClient;

    private static final String FAKE_AUTH_CODE = "123";
    private static final String FAKE_ACCESS_TOKEN = "abc";

    @Rule
    public IntentsTestRule<LoginActivity> loginActivityIntentsTestRule =
            new IntentsTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void registerIdlingResource() {
        Instrumentation instrumentation =
                InstrumentationRegistry.getInstrumentation();
        TpcStudApplication tpcStudApplication =
                (TpcStudApplication) instrumentation.getTargetContext().getApplicationContext();

        TestAppComponent testAppComponent = (TestAppComponent) tpcStudApplication.getTpcStudAppComponent();
        testAppComponent.inject(this);

        /*Espresso.registerIdlingResources(
                loginActivityIntentsTestRule.getActivity().getCountingIdlingResource());*/
        IdlingResource idlingResource = OkHttp3IdlingResource.create(
                "okhttp", okHttpClient);
        Espresso.registerIdlingResources(idlingResource);
    }

    /**
     * Covers the following case:
     * Successful login and no cancellation by the user
     */
    @Test
    public void clickLoginButton_successFulLogin_openDashboard() {

        ActivityResult activityResult = createSuccessfulLoginResultStub(false);

        intending(hasExtraWithKey(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION))
                .respondWith(activityResult);

        onView(withId(R.id.button_login)).perform(click());

        intended(hasComponent(new ComponentName(
                InstrumentationRegistry.getTargetContext(),
                DashboardActivity.class)));

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(R.string.action_logout))
                .check(matches(isDisplayed()));
    }
    /**
     * Covers the following case:
     * Successful login and cancellation by the user
     */
    /*@Test
    public void clickLoginButton_successFulLogin_cancelled_showSnackBar() {

        ActivityResult activityResult = createSuccessfulLoginResultStub(true);

        intending(hasExtraWithKey(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION))
                .respondWith(activityResult);

        onView(withId(R.id.button_login)).perform(click());

        onView(withText(AuthConstants.LOGIN_CANCELLED))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE)));
    }*/

    /**
     * Covers the following case:
     * unsuccessful login
     */
    /*@Test
    public void clickLoginButton_unsuccessfulLogin() {
        throw new AssertionError("To be implemented");
    }*/
    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(
                loginActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }

    private ActivityResult createSuccessfulLoginResultStub(boolean wasCancelled) {
        Intent intent = new Intent();
        AccountKitLoginResult accountKitLoginResult = new FakeAccountKitLoginResult(
                getAccessToken(),
                FAKE_AUTH_CODE,
                wasCancelled,
                null,
                "authState",
                24);
        intent.putExtra(AccountKitLoginResult.RESULT_KEY, accountKitLoginResult);
        return new ActivityResult(Activity.RESULT_OK, intent);
    }


    private AccessToken getAccessToken() {
        return new AccessToken(FAKE_ACCESS_TOKEN,
                "1", "appId", 24, new Date());
    }
}
