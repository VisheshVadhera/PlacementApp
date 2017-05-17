package com.vishesh.placement.core.dagger;

import com.vishesh.placement.auth.services.AuthService;
import com.vishesh.placement.common.services.UserService;
import com.vishesh.placement.dashboard.services.RecruiterService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    public AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Provides
    @Singleton
    public UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    @Provides
    @Singleton
    public RecruiterService provideRecruiterService(Retrofit retrofit) {
        return retrofit.create(RecruiterService.class);
    }
}
