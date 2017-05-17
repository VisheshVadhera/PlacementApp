package com.vishesh.placement.core.dagger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class SchedulersModule {

    @Provides
    @Named("jobScheduler")
    public Scheduler provideJobScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named("postJobScheduler")
    public Scheduler providePostJobScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
