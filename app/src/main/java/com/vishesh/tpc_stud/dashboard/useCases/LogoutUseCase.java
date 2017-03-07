package com.vishesh.tpc_stud.dashboard.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.core.repos.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class LogoutUseCase extends BaseUseCase<Object, Void, Void> {


    private UserRepository userRepository;

    @Inject
    protected LogoutUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                            @Named("postJobScheduler") Scheduler postJobScheduler,
                            UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }

    @Override
    protected Single<Object> buildObservable(Void aVoid, Void aVoid2) {
        return userRepository.logout();
    }
}
