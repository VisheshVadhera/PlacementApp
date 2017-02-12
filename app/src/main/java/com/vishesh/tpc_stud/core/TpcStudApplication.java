package com.vishesh.tpc_stud.core;

import android.app.Application;

import com.vishesh.tpc_stud.core.modules.AppModule;

/**
 * Created by vishesh on 2/2/17.
 */
public class TpcStudApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setupDagger();
    }

    private void setupDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
