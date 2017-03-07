package com.vishesh.tpc_stud.core.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vishesh on 14/2/17.
 */
@Module
public class SchedulersModule {

    @Provides
    @Named("jobScheduler")
    public Scheduler provideJobScheduler() {
        return Schedulers.newThread();
    }

    @Provides
    @Named("postJobScheduler")
    public Scheduler providePostJobScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
