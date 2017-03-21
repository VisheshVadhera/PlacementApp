package com.vishesh.tpc_stud.networkProfile;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.networkProfiles.views.NetworkProfilesActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class NetworkProfilesActivityTest {
    /**
     * Testing strategy:
     * <p>
     * For add network profile flow, partition the state space as follows:
     * <p>
     * 1. Click on add networkProfile, but incorrect url entered.
     * 2. Click on add networkProfile, correct url entered.
     * <p>
     * For view network profile flow, partition the state space as follows:
     * <p>
     * 1. Click on view network profile text, link should be opened in the browser.
     */

    @Rule
    public IntentsTestRule<NetworkProfilesActivity> networkProfilesActivityIntentsTestRule =
            new IntentsTestRule<NetworkProfilesActivity>(NetworkProfilesActivity.class);

    @Before
    public void registerIdlingResource() {
        registerIdlingResources(
                networkProfilesActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void clickOpenNetworkProfile_openProfile() {

        /*ActivityResult activityResult = createOpenProfileResultStub();*/

        onView(withId(R.id.text_profile_item_value))
                .perform(click());

        intending(hasAction(Intent.ACTION_VIEW));
    }

    private ActivityResult createOpenProfileResultStub() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        return new ActivityResult(Activity.RESULT_OK, intent);
    }

}
