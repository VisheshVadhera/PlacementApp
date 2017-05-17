package com.vishesh.placement.dashboard.useCases;

import com.vishesh.placement.core.useCases.BaseUseCase;
import com.vishesh.placement.common.repos.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class LogoutUseCase extends BaseUseCase<Object, Object, Object> {


    private final UserRepository userRepository;

    @Inject
    public LogoutUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                         @Named("postJobScheduler") Scheduler postJobScheduler,
                         UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }


    @Override
    public Single<Object> buildSingle(Object o, Object o2) {
        return userRepository.logout();
    }
}
