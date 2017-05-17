package com.vishesh.placement;

import android.app.Application;

import com.vishesh.placement.core.dagger.AppModule;
import com.vishesh.placement.core.dagger.DaggerAppComponent;
import com.vishesh.placement.core.dagger.DataModule;
import com.vishesh.placement.core.dagger.RetrofitModule;
import com.vishesh.placement.core.dagger.TpcStudAppComponent;

import net.danlew.android.joda.JodaTimeAndroid;

public class TpcStudApplication extends Application {

    TpcStudAppComponent tpcStudAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setupDagger();

        setupJodaTime();
    }

    private void setupJodaTime() {
        JodaTimeAndroid.init(this);
    }

    void setupDagger() {
        tpcStudAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule(getApplicationContext()))
                .retrofitModule(new RetrofitModule(this.getString(R.string.base_url)))
                .build();
    }

    public TpcStudAppComponent getTpcStudAppComponent(){
        return tpcStudAppComponent;
    }
}
