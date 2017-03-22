package com.vishesh.tpc_stud.networkProfile;

import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.networkProfiles.views.NetworkProfilesActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

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
            new IntentsTestRule<>(NetworkProfilesActivity.class);

    @Before
    public void registerIdlingResource() {
        registerIdlingResources(
                networkProfilesActivityIntentsTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void clickOpenNetworkProfile_openProfile() {

        onView(withId(R.id.recycler_view_network_profiles))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        intending(hasAction(Intent.ACTION_VIEW));
    }

    @Test
    public void clickAddNetworkProfile_openEditText() {
        ViewInteraction viewInteraction = onView(
                allOf(withId(R.id.fab_expand_menu_button),
                        withParent(withId(R.id.fab_menu)),
                        isDisplayed()));
        viewInteraction.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withClassName(is("com.getbase.floatingactionbutton.FloatingActionButton")),
                        withParent(withId(R.id.fab_menu)),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.edit_text_url),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
