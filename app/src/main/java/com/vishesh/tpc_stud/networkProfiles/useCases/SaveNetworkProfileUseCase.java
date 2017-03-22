package com.vishesh.tpc_stud.networkProfiles.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.core.repos.UserRepository;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;

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
