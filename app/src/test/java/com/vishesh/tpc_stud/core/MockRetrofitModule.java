package com.vishesh.tpc_stud.core;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

@Module
public class MockRetrofitModule {

    @Provides
    @Singleton
    public MockRetrofit provideMockRetrofit(Retrofit retrofit) {
        return new MockRetrofit.Builder(retrofit)
                .networkBehavior(NetworkBehavior.create())
                .build();
    }

}
