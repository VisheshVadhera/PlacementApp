package com.vishesh.placement.dashboard.services;

import com.vishesh.placement.dashboard.models.Recruiter;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecruiterService {

    @GET("users/{userId}/recruiters")
    Single<List<Recruiter>> getRecruiters(@Path("userId") int userId);

}
