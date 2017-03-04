package com.vishesh.tpc_stud.auth.services;

import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by vishesh on 19/2/17.
 */

public interface UserService {

    @PUT("users/{userId}")
    public Single<User> updateUser(@Path("userId") int userId, @Body User user);

    @GET("users/currentUser")
    public Single<User> getCurrentUser();

    @GET("users/{userId}/profile")
    Single<UserProfile> getProfile(@Path("userId") Integer userId);
}
