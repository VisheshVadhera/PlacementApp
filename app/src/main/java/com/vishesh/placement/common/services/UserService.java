package com.vishesh.placement.common.services;

import com.vishesh.placement.common.models.User;
import com.vishesh.placement.networkProfiles.models.NetworkProfile;
import com.vishesh.placement.dashboard.models.UserProfile;
import com.vishesh.placement.semesterGrades.models.SemesterGrade;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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
    Single<List<NetworkProfile>> getNetworkProfiles(@Path("userId") int userId);

    @POST("users/{userId}/profile/networkProfiles")
    Single<NetworkProfile> saveNetworkProfile(@Path("userId") Integer userId,
                                              @Body NetworkProfile networkProfile);

    @GET("users/{userId}/profile/gpa")
    Single<List<SemesterGrade>> getSemesterGrades(@Path("userId") int userId);
}
