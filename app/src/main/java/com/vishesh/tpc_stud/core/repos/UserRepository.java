package com.vishesh.tpc_stud.core.repos;

import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.services.AuthService;
import com.vishesh.tpc_stud.auth.services.UserService;
import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by vishesh on 18/2/17.
 */

public class UserRepository {

    private final AuthService authService;
    private UserService userService;

    @Inject
    public UserRepository(AuthService authService,
                          UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    public Single<AccessToken> emailLogin(Map<String, String> params) {
        return authService.emailLogin(params);
    }

    public Single<User> updateUser(final int userId, User user) {
        return userService.updateUser(userId, user);
    }

    public Single<User> getCurrentUser() {
        return userService.getCurrentUser();
    }

    public Single<Object> logout() {
        return userService.logout();
    }

    public Single<UserProfile> getProfile(Integer userId) {
        return userService.getProfile(userId);
    }
}
