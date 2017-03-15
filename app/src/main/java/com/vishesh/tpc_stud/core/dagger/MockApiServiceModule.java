package com.vishesh.tpc_stud.core.dagger;

import com.vishesh.tpc_stud.auth.services.AuthService;
import com.vishesh.tpc_stud.auth.services.MockAuthService;
import com.vishesh.tpc_stud.auth.services.MockUserService;
import com.vishesh.tpc_stud.auth.services.UserService;
import com.vishesh.tpc_stud.dashboard.services.MockRecruiterService;
import com.vishesh.tpc_stud.dashboard.services.RecruiterService;

import dagger.Module;
import dagger.Provides;
import retrofit2.mock.MockRetrofit;

@Module
public class MockApiServiceModule {

    @Provides
    @PerActivity
    public AuthService provideAuthService(MockRetrofit mockRetrofit) {
        return new MockAuthService(mockRetrofit);
    }

    @Provides
    @PerActivity
    public UserService provideUserService(MockRetrofit mockRetrofit) {
        return new MockUserService(mockRetrofit);
    }

    @Provides
    @PerActivity
    public RecruiterService provideRecruiterService(MockRetrofit mockRetrofit) {
        return new MockRecruiterService(mockRetrofit);
    }
}
