package com.vishesh.tpc_stud.core.repos;

import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.services.AuthService;
import com.vishesh.tpc_stud.auth.services.UserService;
import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

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
        return authService.emailLogin(params)
                .map(new Function<AccessToken, AccessToken>() {
                    @Override
                    public AccessToken apply(AccessToken accessToken) throws Exception {
                        localCache.saveAccessToken(accessToken.getAccessToken());
                        return accessToken;
                    }
                });
    }

    public Single<User> updateUser(final Integer userId, User user) {
        return userService.updateUser(userId, user);
    }

    public Single<User> getCurrentUser() {
        return userService.getCurrentUser()
                .map(new Function<User, User>() {
                    @Override
                    public User apply(User user) throws Exception {
                        localCache.saveUserId(user.getId());
                        return user;
                    }
                });
    }

    public Single<Object> logout() {
        return userService.logout();
    }

    public Single<UserProfile> getProfile(Integer userId) {
        return userService.getProfile(userId);
    }
}
