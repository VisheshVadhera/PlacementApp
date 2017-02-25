package com.vishesh.tpc_stud.dashboard.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.core.repos.UserRepository;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by vishesh on 24/2/17.
 */
public class GetProfileUseCase extends BaseUseCase<UserProfile, Integer, Object>{

    private final UserRepository userRepository;

    @Inject
    protected GetProfileUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                                @Named("postJobScheduler") Scheduler postJobScheduler,
                                CompositeDisposable compositeDisposable,
                                UserRepository userRepository) {
        super(jobScheduler, postJobScheduler, compositeDisposable);
        this.userRepository = userRepository;
    }

    @Override
    protected Single<UserProfile> buildObservable(Integer userId, Object o) {
        return userRepository.getProfile(userId);
    }
}
