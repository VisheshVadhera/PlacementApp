package com.vishesh.tpc_stud.core;

import com.vishesh.tpc_stud.core.helpers.Bus;
import com.vishesh.tpc_stud.core.modules.AppModule;
import com.vishesh.tpc_stud.core.modules.MockDataModule;
import com.vishesh.tpc_stud.core.modules.RetrofitModule;
import com.vishesh.tpc_stud.core.repos.LocalCache;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import retrofit2.mock.MockRetrofit;

@Singleton
@Component(modules = {
        AppModule.class, MockRetrofitModule.class,
        RetrofitModule.class, MockDataModule.class
})
public interface TestAppComponent {

    Retrofit retrofit();

    LocalCache localCache();

    MockRetrofit mockRetrofit();

    Bus bus();
}
