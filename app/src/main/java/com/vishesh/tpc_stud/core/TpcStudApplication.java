package com.vishesh.tpc_stud.core;

import android.app.Application;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.modules.ApiServiceModule;
import com.vishesh.tpc_stud.core.modules.AppModule;
import com.vishesh.tpc_stud.core.modules.RetrofitModule;
import com.vishesh.tpc_stud.core.modules.SchedulersModule;

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
                .schedulersModule(new SchedulersModule())
                .retrofitModule(new RetrofitModule(this.getString(R.string.base_url)))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
