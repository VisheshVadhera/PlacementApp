package com.vishesh.tpc_stud.core.modules;

import com.vishesh.tpc_stud.auth.services.AuthService;
import com.vishesh.tpc_stud.auth.services.UserService;
import com.vishesh.tpc_stud.core.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by vishesh on 15/2/17.
 */
@Module
public class ApiServiceModule {

    @Provides
    @PerActivity
    public AuthService provideAuthService(Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Provides
    @PerActivity
    public UserService provideUserService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }
}
