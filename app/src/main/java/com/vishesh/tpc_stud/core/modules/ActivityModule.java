package com.vishesh.tpc_stud.core.modules;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.vishesh.tpc_stud.core.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Context context, Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public Context provideContext() {
        return activity;
    }

    @Provides
    @PerActivity
    public FragmentManager provideFragmentManager() {
        return ((FragmentActivity) activity).getSupportFragmentManager();
    }
}
