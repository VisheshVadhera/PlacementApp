package com.vishesh.tpc_stud.auth.services;

import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.mock.MockRetrofit;

public class MockUserService implements UserService {

    private final MockRetrofit mockRetrofit;

    public MockUserService(MockRetrofit mockRetrofit) {
        this.mockRetrofit = mockRetrofit;
    }

    @Override
    public Single<User> updateUser(@Path("userId") int userId, @Body User user) {
        return null;
    }

    @Override
    public Single<User> getCurrentUser() {
        return null;
    }

    @Override
    public Single<UserProfile> getProfile(@Path("userId") Integer userId) {
        return null;
    }
}
