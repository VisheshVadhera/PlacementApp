package com.vishesh.tpc_stud.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.ActivityComponent;
import com.vishesh.tpc_stud.core.helpers.DependencyInjector;
import com.vishesh.tpc_stud.core.views.BaseActivity;

public class DashboardActivity
        extends BaseActivity
        implements DependencyInjector<ActivityComponent> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if(savedInstanceState == null){
            addFragment(R.id.fragment_container, new DashboardFragment());
        }
    }

    @Override
    public ActivityComponent getInjector() {
        return null;
    }
}
