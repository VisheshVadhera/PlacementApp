package com.vishesh.placement.dashboard.useCases;

import com.vishesh.placement.core.useCases.BaseUseCase;
import com.vishesh.placement.dashboard.models.Recruiter;
import com.vishesh.placement.dashboard.repos.RecruiterRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class GetRecruitersUseCase extends BaseUseCase<List<Recruiter>, Integer, Object>{

    private final RecruiterRepository recruiterRepository;

    @Inject
    public GetRecruitersUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                                @Named("postJobScheduler") Scheduler postJobScheduler,
                                RecruiterRepository recruiterRepository) {
        super(jobScheduler, postJobScheduler);
        this.recruiterRepository = recruiterRepository;
    }


    @Override
    public Single<List<Recruiter>> buildSingle(Integer userId, Object o) {
        return recruiterRepository.getRecruiters(userId);
    }
}
