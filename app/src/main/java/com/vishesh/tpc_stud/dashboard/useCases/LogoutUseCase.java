package com.vishesh.tpc_stud.dashboard.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.core.repos.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class LogoutUseCase extends BaseUseCase<Object, Object, Object> {


    private UserRepository userRepository;

    @Inject
    public LogoutUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                         @Named("postJobScheduler") Scheduler postJobScheduler,
                         UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }


    @Override
    public Single<Object> buildObservable(Object o, Object o2) {
        return userRepository.logout();
    }
}
