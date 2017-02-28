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

    private static final double NETWORK_DELAY_SECONDS = 1.5;
    private static final int NETWORK_DELAY_VARIANCE_PERCENT = 40;
    private static final int NETWORK_FAILURE_PERCENT = 10;

    @Provides
    @Singleton
    public MockRetrofit provideMockRetrofit(Retrofit retrofit) {
        
        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setDelay((long) NETWORK_DELAY_SECONDS, TimeUnit.SECONDS);
        behavior.setVariancePercent(NETWORK_DELAY_VARIANCE_PERCENT);
        behavior.setFailurePercent(NETWORK_FAILURE_PERCENT);

        return new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

}
