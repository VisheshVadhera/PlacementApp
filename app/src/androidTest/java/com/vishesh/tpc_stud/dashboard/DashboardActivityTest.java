package com.vishesh.tpc_stud.dashboard;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.dashboard.views.DashboardActivity;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
}
