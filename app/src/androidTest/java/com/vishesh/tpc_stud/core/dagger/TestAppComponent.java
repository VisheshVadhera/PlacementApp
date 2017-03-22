package com.vishesh.tpc_stud.core.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class, MockRetrofitModule.class,
        RetrofitModule.class, MockDataModule.class,
        SchedulersModule.class, MockApiServiceModule.class})
public interface TestAppComponent extends TpcStudAppComponent {

}
