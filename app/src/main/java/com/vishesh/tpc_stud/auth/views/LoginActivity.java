package com.vishesh.tpc_stud.auth.views;

import android.os.Bundle;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.ActivityComponent;
import com.vishesh.tpc_stud.core.DaggerActivityComponent;
import com.vishesh.tpc_stud.core.helpers.DependencyInjector;
import com.vishesh.tpc_stud.core.views.BaseActivity;

public class LoginActivity
        extends BaseActivity
        implements DependencyInjector<ActivityComponent> {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new LoginFragment());
        }
    }

    private void initializeInjector() {
        activityComponent = DaggerActivityComponent
                .builder()
                .activityModule(getActivityModule())
                .appComponent(getApplicationComponent())
                .build();
    }

    @Override
    public ActivityComponent getInjector() {
        return activityComponent;
    }
}
