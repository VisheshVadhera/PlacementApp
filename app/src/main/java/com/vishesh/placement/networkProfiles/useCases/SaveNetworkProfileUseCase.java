package com.vishesh.placement.networkProfiles.useCases;

import com.vishesh.placement.core.useCases.BaseUseCase;
import com.vishesh.placement.common.repos.UserRepository;
import com.vishesh.placement.networkProfiles.models.NetworkProfile;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class SaveNetworkProfileUseCase extends BaseUseCase<NetworkProfile, Integer, NetworkProfile>{

    private final UserRepository userRepository;

    @Inject
    protected SaveNetworkProfileUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                                        @Named("postJobScheduler") Scheduler postJobScheduler,
                                        UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }

    @Override
    public Single<NetworkProfile> buildSingle(Integer userId, NetworkProfile networkProfile) {
        return userRepository.saveNetworkProfile(userId, networkProfile);
    }
}
