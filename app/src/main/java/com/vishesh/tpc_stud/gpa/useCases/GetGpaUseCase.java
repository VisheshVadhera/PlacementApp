package com.vishesh.tpc_stud.gpa.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.core.repos.UserRepository;
import com.vishesh.tpc_stud.gpa.models.Gpa;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class GetGpaUseCase extends BaseUseCase<Gpa, Integer, Object> {

    private final UserRepository userRepository;

    @Inject
    public GetGpaUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                         @Named("postJobScheduler") Scheduler postJobScheduler,
                         UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }

    @Override
    public Single<Gpa> buildSingle(Integer userId, Object o) {
        return userRepository.getGpa(userId);
    }
}
