package com.vishesh.placement.auth.views;

import android.os.Bundle;

import com.vishesh.placement.R;
import com.vishesh.placement.core.views.BaseActivity;

public class LoginActivity
        extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new LoginFragment());
        }
    }

}
