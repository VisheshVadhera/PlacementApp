package com.vishesh.tpc_stud.core.views;

import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.vishesh.tpc_stud.TpcStudApplication;
import com.vishesh.tpc_stud.core.dagger.TpcStudAppComponent;
import com.vishesh.tpc_stud.core.idlingResources.EspressoIdlingResource;

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

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
