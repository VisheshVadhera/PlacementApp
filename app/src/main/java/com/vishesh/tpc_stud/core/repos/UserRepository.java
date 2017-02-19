package com.vishesh.tpc_stud.core.repos;

import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.services.AuthService;
import com.vishesh.tpc_stud.auth.services.UserService;
import com.vishesh.tpc_stud.core.models.User;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by vishesh on 18/2/17.
 */

public class UserRepository {

    private final AuthService authService;
    private final LocalCache localCache;
    private UserService userService;

    @Inject
    public UserRepository(AuthService authService,
                          LocalCache localCache,
                          UserService userService) {
        this.authService = authService;
        this.localCache = localCache;
        this.userService = userService;
    }

    public Single<AccessToken> emailLogin(Map<String, String> params) {
        return authService.emailLogin(params);
    }

    public Single<Object> saveAccessToken(AccessToken accessToken) {
        localCache.saveAccessToken(accessToken.getAccessToken());
        return Single.just(new Object());
    }

    public Single<User> updateUser(User user) {
        return userService.updateUser(localCache.getUserId(), user);
    }
}
