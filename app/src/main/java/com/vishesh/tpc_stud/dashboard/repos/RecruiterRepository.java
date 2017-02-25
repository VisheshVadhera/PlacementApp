package com.vishesh.tpc_stud.dashboard.repos;

import com.vishesh.tpc_stud.dashboard.models.Recruiter;
import com.vishesh.tpc_stud.dashboard.services.RecruiterService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RecruiterRepository {

    private final RecruiterService recruiterService;

    @Inject
    public RecruiterRepository(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    public Single<List<Recruiter>> getRecruiters(int userId){
        return recruiterService.getRecruiters(userId);
    }

}
