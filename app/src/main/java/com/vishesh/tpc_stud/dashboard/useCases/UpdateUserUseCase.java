package com.vishesh.tpc_stud.dashboard.useCases;

import com.vishesh.tpc_stud.core.useCases.BaseUseCase;
import com.vishesh.tpc_stud.common.models.User;
import com.vishesh.tpc_stud.common.repos.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class UpdateUserUseCase extends BaseUseCase<User, Integer, User>{

    private final UserRepository userRepository;

    @Inject
    public UpdateUserUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                             @Named("postJobScheduler") Scheduler postJobScheduler,
                             UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }

    @Override
    public Single<User> buildSingle(Integer userId, User user) {
        return userRepository.updateUser(userId, user);
    }
}
