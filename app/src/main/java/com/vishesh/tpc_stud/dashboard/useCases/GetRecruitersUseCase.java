package com.vishesh.tpc_stud.dashboard.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.dashboard.models.Recruiter;
import com.vishesh.tpc_stud.dashboard.repos.RecruiterRepository;

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
    public Single<List<Recruiter>> buildObservable(Integer userId, Object o) {
        return recruiterRepository.getRecruiters(userId);
    }
}
