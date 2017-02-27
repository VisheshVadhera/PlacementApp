package com.vishesh.tpc_stud.core;

import java.util.concurrent.TimeUnit;

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
        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setDelay((long) 1.5, TimeUnit.SECONDS);
        behavior.setVariancePercent(40);
        behavior.setFailurePercent(10);

        return new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

}
