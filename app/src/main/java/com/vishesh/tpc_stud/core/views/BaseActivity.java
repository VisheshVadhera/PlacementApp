package com.vishesh.tpc_stud.core.views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vishesh.tpc_stud.core.AppComponent;
import com.vishesh.tpc_stud.core.TpcStudApplication;
import com.vishesh.tpc_stud.core.modules.ActivityModule;

/**
 * Created by vishesh on 12/2/17.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment)
                .commit();
    }

    protected AppComponent getApplicationComponent(){
        return ((TpcStudApplication) getApplication()).getAppComponent();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }
}
