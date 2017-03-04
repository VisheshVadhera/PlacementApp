package com.vishesh.tpc_stud.splash;

import android.os.Bundle;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.ActivityComponent;
import com.vishesh.tpc_stud.core.DaggerActivityComponent;
import com.vishesh.tpc_stud.core.views.BaseActivity;

public class SplashActivity
        extends BaseActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container_splash, new SplashFragment());
        }
    }

    private void initializeInjector() {
        activityComponent = DaggerActivityComponent
                .builder()
                .activityModule(getActivityModule())
                .appComponent(getApplicationComponent())
                .build();
    }
}
