package com.vishesh.tpc_stud.dashboard;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.dashboard.views.DashboardActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DashboardActivityTest {

    /**
     * Testing Strategy
     *
     * Partition the possible user interactions as follows:
     *
     * 1. Click on the logout menu item, and login screen should pop up.
     * 2. Swipe left and profile fragment should be made visible.
     * 3. Click on a recruiter item in the recruiters tab & the full info of the recruiter should be opened //TODO
     * 4. Click on the network profile item and a screen containing the all network profiles should be made visible //TODO
     * 5. Click on the resume upload button and the file upload should begin. //TODO
     */

    @Rule
    public ActivityTestRule<DashboardActivity> dashboardActivityActivityTestRule =
            new ActivityTestRule<DashboardActivity>(DashboardActivity.class);

    @Before
    public void registerIdlingResource() {
        Espresso.registerIdlingResources(dashboardActivityActivityTestRule
                .getActivity().getCountingIdlingResource());
    }

    /**
     * Covers the following:
     * Click on the logout menu item, and login screen should pop up.
     */
    @Test
    public void onLogoutMenuItemClicked_showLoginScreen() {

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(R.string.action_logout))
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.button_login))
                .check(matches(isDisplayed()));
    }

    /**
     * Covers the following:
     * Swipe left and profile fragment should be made visible.
     */
    @Test
    public void swipeLeft_showProfileTab() {

        String title = dashboardActivityActivityTestRule
                .getActivity()
                .getString(R.string.dashboard_tab_profile_title);

        onView(withId(R.id.view_pager_dashboard))
                .perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)));

        onView(isAssignableFrom(Toolbar.class))
                .check(matches(
                        withToolbarTitle(
                                Matchers.<CharSequence>is(title))));
    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(dashboardActivityActivityTestRule
                .getActivity().getCountingIdlingResource());
    }

    private Matcher<Object> withToolbarTitle(final Matcher<CharSequence> charSequenceMatcher) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with toolbar title: ");
                charSequenceMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(Toolbar toolbar) {
                return charSequenceMatcher.matches(toolbar.getTitle());
            }
        };
    }

    private ViewAction withCustomConstraints(final ViewAction action, final Matcher<View> constraints) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return constraints;
            }

            @Override
            public String getDescription() {
                return action.getDescription();
            }

            @Override
            public void perform(UiController uiController, View view) {
                action.perform(uiController, view);
            }
        };
    }
}
