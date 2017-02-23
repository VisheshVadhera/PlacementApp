package com.vishesh.tpc_stud.dashboard.repos;

import com.vishesh.tpc_stud.dashboard.services.RecruiterService;

public class RecruiterRepository {

    private final RecruiterService recruiterService;

    public RecruiterRepository(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }


}
