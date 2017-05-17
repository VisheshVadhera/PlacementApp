package com.vishesh.placement.networkProfiles.views;

import android.os.Bundle;

import com.vishesh.placement.R;
import com.vishesh.placement.core.views.BaseActivity;


public class NetworkProfilesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_profiles);

        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new NetworkProfilesFragment());
        }
    }

}
