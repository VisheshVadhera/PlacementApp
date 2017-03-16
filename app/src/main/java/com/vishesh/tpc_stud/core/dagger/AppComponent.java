package com.vishesh.tpc_stud.core.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vishesh on 12/2/17.
 */
@Singleton
@Component(modules = {AppModule.class, MockRetrofitModule.class,
        RetrofitModule.class, DataModule.class,
        SchedulersModule.class, MockApiServiceModule.class})
public interface AppComponent extends TpcStudAppComponent {

}
