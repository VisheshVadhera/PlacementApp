package com.vishesh.tpc_stud.core.modules;

import com.vishesh.tpc_stud.auth.services.AuthService;
import com.vishesh.tpc_stud.auth.services.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by vishesh on 15/2/17.
 */
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
}
