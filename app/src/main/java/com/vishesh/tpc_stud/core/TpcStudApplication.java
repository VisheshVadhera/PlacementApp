package com.vishesh.tpc_stud.core;

import android.app.Application;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.dagger.AppModule;
import com.vishesh.tpc_stud.core.dagger.TpcStudAppComponent;
import com.vishesh.tpc_stud.core.dagger.DaggerAppComponent;
import com.vishesh.tpc_stud.core.dagger.DataModule;
import com.vishesh.tpc_stud.core.dagger.RetrofitModule;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by vishesh on 2/2/17.
 */
public class TpcStudApplication extends Application {

    protected TpcStudAppComponent tpcStudAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setupDagger();

        setupJodaTime();
    }

    private void setupJodaTime() {
        JodaTimeAndroid.init(this);
    }

    protected void setupDagger() {
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
