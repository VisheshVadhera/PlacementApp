package com.vishesh.tpc_stud.splash;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.splash.views.SplashActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> splashActivityActivityTestRule =
            new ActivityTestRule<>(SplashActivity.class);


    @Test
    public void testContainsSplashFragment() {
        Fragment splashFragment = splashActivityActivityTestRule
                .getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container_splash);

        assertThat(splashFragment)
                .isNotNull();
    }
}
