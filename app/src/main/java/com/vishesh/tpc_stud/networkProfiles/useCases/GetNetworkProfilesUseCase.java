package com.vishesh.tpc_stud.networkProfiles.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.core.repos.UserRepository;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class GetNetworkProfilesUseCase
        extends BaseUseCase<List<NetworkProfile>, Integer, Object> {

    private final UserRepository userRepository;

    @Inject
    public GetNetworkProfilesUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                                     @Named("postJobScheduler") Scheduler postJobScheduler,
                                     UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }

    @Override
    public Single<List<NetworkProfile>> buildSingle(Integer userId, Object o) {
        return userRepository.getNetworkProfiles(userId);
    }
}
