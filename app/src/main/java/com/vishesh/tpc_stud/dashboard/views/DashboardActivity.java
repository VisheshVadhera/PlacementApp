package com.vishesh.tpc_stud.dashboard.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.views.BaseActivity;

public class DashboardActivity
        extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            addFragment(R.id.fragment_container, new DashboardFragment());
        }
    }
}
