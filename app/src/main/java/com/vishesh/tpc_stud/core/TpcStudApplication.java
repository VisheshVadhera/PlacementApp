package com.vishesh.tpc_stud.core;

import android.app.Application;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.modules.AppModule;
import com.vishesh.tpc_stud.core.modules.DataModule;
import com.vishesh.tpc_stud.core.modules.RetrofitModule;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by vishesh on 2/2/17.
 */
public class TpcStudApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setupDagger();

        setupJodaTime();
    }

    private void setupJodaTime() {
        JodaTimeAndroid.init(this);
    }

    private void setupDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule(getApplicationContext()))
                .retrofitModule(new RetrofitModule(this.getString(R.string.base_url)))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
