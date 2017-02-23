package com.vishesh.tpc_stud.dashboard.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.dashboard.models.Job;
import com.vishesh.tpc_stud.dashboard.repos.RecruiterRepository;

import java.util.List;

import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class GetRecruitersUseCase extends BaseUseCase<List<Job>, Integer, Void>{

    private final RecruiterRepository recruiterRepository;

    protected GetRecruitersUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                                   @Named("postJobScheduler") Scheduler postJobScheduler,
                                   CompositeDisposable compositeDisposable,
                                   RecruiterRepository recruiterRepository) {
        super(jobScheduler, postJobScheduler, compositeDisposable);
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    protected Single<List<Job>> buildObservable(Integer integer, Void aVoid) {
        return null;
    }
}
