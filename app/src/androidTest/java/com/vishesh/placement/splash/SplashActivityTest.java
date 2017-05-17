package com.vishesh.placement.splash;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import com.vishesh.placement.R;
import com.vishesh.placement.splash.views.SplashActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

        Assert.assertNotNull(splashFragment);
    }
}
