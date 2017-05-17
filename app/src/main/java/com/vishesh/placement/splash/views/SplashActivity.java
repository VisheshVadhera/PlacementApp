package com.vishesh.placement.splash.views;

import android.os.Bundle;

import com.vishesh.placement.R;
import com.vishesh.placement.core.views.BaseActivity;

public class SplashActivity
        extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container_splash, new SplashFragment());
        }
    }
}
