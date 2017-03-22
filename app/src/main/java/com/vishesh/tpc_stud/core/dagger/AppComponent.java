package com.vishesh.tpc_stud.core.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, MockRetrofitModule.class,
        RetrofitModule.class, DataModule.class,
        SchedulersModule.class, MockApiServiceModule.class})
public interface AppComponent extends TpcStudAppComponent {

}
