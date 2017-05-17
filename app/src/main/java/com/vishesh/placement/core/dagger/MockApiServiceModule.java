package com.vishesh.placement.core.dagger;

import com.vishesh.placement.auth.services.AuthService;
import com.vishesh.placement.auth.services.MockAuthService;
import com.vishesh.placement.common.services.MockUserService;
import com.vishesh.placement.common.services.UserService;
import com.vishesh.placement.dashboard.services.MockRecruiterService;
import com.vishesh.placement.dashboard.services.RecruiterService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.mock.MockRetrofit;

@Module
public class MockApiServiceModule {

    @Provides
    @Singleton
    public AuthService provideAuthService(MockRetrofit mockRetrofit) {
        return new MockAuthService(mockRetrofit);
    }

    @Provides
    @Singleton
    public UserService provideUserService(MockRetrofit mockRetrofit) {
        return new MockUserService(mockRetrofit);
    }

    @Provides
    @Singleton
    public RecruiterService provideRecruiterService(MockRetrofit mockRetrofit) {
        return new MockRecruiterService(mockRetrofit);
    }
}
