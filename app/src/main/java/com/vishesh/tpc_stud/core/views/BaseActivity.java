package com.vishesh.tpc_stud.core.views;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.vishesh.tpc_stud.core.TpcStudApplication;
import com.vishesh.tpc_stud.core.dagger.TpcStudAppComponent;

/**
 * Created by vishesh on 12/2/17.
 */
public class BaseActivity extends AppCompatActivity {

    protected void addFragment(int containerViewId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

    public TpcStudAppComponent getApplicationComponent(){
        return ((TpcStudApplication) getApplication()).getTpcStudAppComponent();
    }
}
