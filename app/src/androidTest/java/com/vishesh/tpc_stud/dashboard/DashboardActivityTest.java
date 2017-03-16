package com.vishesh.tpc_stud.dashboard;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.dashboard.views.DashboardActivity;

import org.assertj.core.api.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DashboardActivityTest {

    @Rule
    public ActivityTestRule<DashboardActivity> dashboardActivityActivityTestRule =
            new ActivityTestRule<DashboardActivity>(DashboardActivity.class);

    @Test
    public void testContainsDashboardFragment() {
        Fragment fragment = dashboardActivityActivityTestRule
                .getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        Assertions.assertThat(fragment)
                .isNotNull();
    }

    @Test
    public void onMenuClicked_showLogoutOptionInOverflowMenu() {

        onView(withId(R.id.toolbar))
                .perform(click());

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        onView(withText(R.string.action_logout))
                .check(matches(isDisplayed()));
    }
}
