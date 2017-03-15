package com.vishesh.tpc_stud.core.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.vishesh.tpc_stud.core.TpcStudApplication;
import com.vishesh.tpc_stud.core.dagger.ActivityModule;
import com.vishesh.tpc_stud.core.dagger.TpcStudAppComponent;

/**
 * Created by vishesh on 12/2/17.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

    protected TpcStudAppComponent getApplicationComponent(){
        return ((TpcStudApplication) getApplication()).getTpcStudAppComponent();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }
}
