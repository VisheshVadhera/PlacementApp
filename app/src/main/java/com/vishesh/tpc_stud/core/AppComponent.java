package com.vishesh.tpc_stud.core;

import com.vishesh.tpc_stud.core.modules.AppModule;
import com.vishesh.tpc_stud.core.modules.DataModule;
import com.vishesh.tpc_stud.core.modules.RetrofitModule;
import com.vishesh.tpc_stud.core.repos.LocalCache;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import retrofit2.mock.MockRetrofit;

/**
 * Created by vishesh on 12/2/17.
 */
@Singleton
@Component(modules = {AppModule.class, MockRetrofitModule.class,
        RetrofitModule.class, DataModule.class})
public interface AppComponent {

    Retrofit retrofit();

    LocalCache localCache();

    MockRetrofit mockRetrofit();
}
