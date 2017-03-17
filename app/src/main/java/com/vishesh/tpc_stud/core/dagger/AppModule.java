package com.vishesh.tpc_stud.core.dagger;

import android.content.Context;

import com.vishesh.tpc_stud.core.TpcStudApplication;
import com.vishesh.tpc_stud.core.helpers.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vishesh on 12/2/17.
 */
@Module
public class AppModule {

    private final TpcStudApplication tpcStudApplication;

    public AppModule(TpcStudApplication tpcStudApplication) {
        this.tpcStudApplication = tpcStudApplication;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return tpcStudApplication;
    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }
}
