package com.vishesh.tpc_stud.dashboard.services;

import com.vishesh.tpc_stud.dashboard.models.Recruiter;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecruiterService {

    @GET("users/{userId}/recruiters")
    public Single<List<Recruiter>> getRecruiters(@Path("userId") int userId);

}
