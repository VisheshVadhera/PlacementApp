package com.vishesh.tpc_stud.dashboard.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.dashboard.models.Recruiter;
import com.vishesh.tpc_stud.dashboard.repos.RecruiterRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class GetRecruitersUseCase extends BaseUseCase<List<Recruiter>, Integer, Void>{

    private final RecruiterRepository recruiterRepository;

    @Inject
    protected GetRecruitersUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                                   @Named("postJobScheduler") Scheduler postJobScheduler,
                                   CompositeDisposable compositeDisposable,
                                   RecruiterRepository recruiterRepository) {
        super(jobScheduler, postJobScheduler, compositeDisposable);
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    protected Single<List<Recruiter>> buildObservable(Integer userId, Void aVoid) {
        return recruiterRepository.getRecruiters(userId);
    }
}
