package com.vishesh.tpc_stud.core.dagger;

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

        return new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

}
