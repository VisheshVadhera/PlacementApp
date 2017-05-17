package com.vishesh.placement.dashboard.useCases;

import com.vishesh.placement.core.useCases.BaseUseCase;
import com.vishesh.placement.common.repos.UserRepository;
import com.vishesh.placement.dashboard.models.UserProfile;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class GetProfileUseCase extends BaseUseCase<UserProfile, Integer, Object>{

    private final UserRepository userRepository;

    @Inject
    public GetProfileUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                             @Named("postJobScheduler") Scheduler postJobScheduler,
                             UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }

    @Override
    public Single<UserProfile> buildSingle(Integer userId, Object o) {
        return userRepository.getProfile(userId);
    }
}
