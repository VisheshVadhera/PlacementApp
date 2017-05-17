package com.vishesh.placement.dashboard.views;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vishesh.placement.R;
import com.vishesh.placement.core.views.BaseActivity;

public class DashboardActivity
        extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new DashboardFragment());
        }
    }
}
