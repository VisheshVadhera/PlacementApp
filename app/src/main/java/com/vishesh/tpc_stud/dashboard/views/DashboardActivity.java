package com.vishesh.tpc_stud.dashboard.views;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.ActivityComponent;
import com.vishesh.tpc_stud.core.DaggerActivityComponent;
import com.vishesh.tpc_stud.core.helpers.DependencyInjector;
import com.vishesh.tpc_stud.core.views.BaseActivity;

public class DashboardActivity
        extends BaseActivity
        implements DependencyInjector<ActivityComponent> {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initializeInjector();

        if(savedInstanceState == null){
            addFragment(R.id.fragment_container, new DashboardFragment());
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
