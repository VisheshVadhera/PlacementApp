package com.vishesh.tpc_stud.core.repos;

import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.services.AuthService;
import com.vishesh.tpc_stud.auth.services.UserService;
import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserRepository {

    private final AuthService authService;
    private final UserService userService;

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

    public Single<UserProfile> getProfile(int userId) {
        return userService.getProfile(userId);
    }

    public Single<List<NetworkProfile>> getNetworkProfiles(int userId) {
        return userService.getNetworkProfiles(userId);
    }

    public Single<NetworkProfile> saveNetworkProfile(int userId, NetworkProfile networkProfile) {
        return userService.saveNetworkProfile(userId, networkProfile);
    }
}
