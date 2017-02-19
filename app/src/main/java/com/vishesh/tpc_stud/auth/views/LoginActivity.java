package com.vishesh.tpc_stud.auth.views;

import android.os.Bundle;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.views.BaseActivity;

public class LoginActivity extends BaseActivity {

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

    }
}
