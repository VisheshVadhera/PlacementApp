package com.vishesh.tpc_stud.dashboard;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.LargeTest;
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
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DashboardActivityTest {

    /**
     * Testing Strategy
     * <p>
     * Partition the possible user interactions as follows:
     * <p>
     * 1. Click on the logout menu item, and login screen should pop up.
     * 2. Swipe left and profile fragment should be made visible.
     * 3. Click on a recruiter item in the recruiters tab & the full info of the recruiter should be opened //TODO
     * 4. Click on the network profile item and a screen containing the all network profiles should be made visible //TODO
     * 5. Click on the resume upload button and the file upload should begin. //TODO
     * 6. Click on the profile tab, then click on the network profile item, network profiles screen should be opened.
     * 7. Click on the profile tab, then click on the gpa item, semester grades screen should be opened.
     */

    @Rule
    public IntentsTestRule<DashboardActivity> dashboardActivityActivityTestRule =
            new IntentsTestRule<>(DashboardActivity.class);

    @Before
    public void registerIdlingResource() {
        Espresso.registerIdlingResources(dashboardActivityActivityTestRule
                .getActivity().getCountingIdlingResource());
    }

    /**
     * Covers the following:
     * Click on the logout menu item, and login screen should pop up.
     */
    @Ignore
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
    @Ignore
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

    /**
     * Covers the following:
     * <p>
     * Click on the profile tab, then click on the network profile item, network profiles screen should be opened.
     */
    @Test
    public void onProfileTabClicked_onNetworkProfileItemClicked_openNetworkProfilesScreen() {

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.view_pager_dashboard), isDisplayed()));
        viewPager.perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)));

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.image_network_profile_item), isDisplayed()));
        imageButton.perform(click());

        String s = dashboardActivityActivityTestRule
                .getActivity()
                .getString(R.string.title_activity_network_profiles);

        onView(isAssignableFrom(Toolbar.class))
                .check(matches(
                        withToolbarTitle(
                                Matchers.<CharSequence>is(s))));
    }

    /**
     * Covers the following:
     * <p>
     * Click on the profile tab, then click on the gpa item, semester grades screen should be opened.
     */
    @Test
    public void onProfileTabClicked_onGpaItemClicked_openSemesterGradesScreen() {

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.view_pager_dashboard), isDisplayed()));
        viewPager.perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)));

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view_profile), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        String s = dashboardActivityActivityTestRule
                .getActivity()
                .getString(R.string.title_activity_semester_grades);

        onView(isAssignableFrom(Toolbar.class))
                .check(matches(
                        withToolbarTitle(
                                Matchers.<CharSequence>is(s))));
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
