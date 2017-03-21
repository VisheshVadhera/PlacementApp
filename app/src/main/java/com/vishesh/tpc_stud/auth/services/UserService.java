package com.vishesh.tpc_stud.auth.services;

import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by vishesh on 19/2/17.
 */

public interface UserService {

    @PUT("users/{userId}")
    Single<User> updateUser(@Path("userId") int userId, @Body User user);

    @GET("users/currentUser")
    Single<User> getCurrentUser();

    @GET("users/{userId}/profile")
    Single<UserProfile> getProfile(@Path("userId") Integer userId);

    @POST("users/{userId}/logout")
    Single<Object> logout();

    @GET("users/{userId}/profile/networkProfiles")
    Single<List<NetworkProfile>> getNetworkProfiles(int userId);

    @POST("users/{userId}/profile/networkProfiles")
    Single<NetworkProfile> saveNetworkProfile(@Path("userId") Integer userId,
                                              @Body NetworkProfile networkProfile);
}
