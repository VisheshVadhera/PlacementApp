package com.vishesh.tpc_stud.core;

import com.vishesh.tpc_stud.auth.services.AuthService;
import com.vishesh.tpc_stud.auth.services.MockAuthService;
import com.vishesh.tpc_stud.auth.services.MockUserService;
import com.vishesh.tpc_stud.auth.services.UserService;
import com.vishesh.tpc_stud.dashboard.services.MockRecruiterService;
import com.vishesh.tpc_stud.dashboard.services.RecruiterService;

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
