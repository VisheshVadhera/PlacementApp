package com.vishesh.tpc_stud.auth.useCases;

import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.core.useCases.BaseUseCase;
import com.vishesh.tpc_stud.common.repos.UserRepository;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class LoginUseCase extends BaseUseCase<AccessToken, Map<String, String>, Object> {

    private final UserRepository userRepository;

    @Inject
    public LoginUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                           @Named("postJobScheduler") Scheduler postJobScheduler,
                           UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }

    @Override
    public Single<AccessToken> buildSingle(Map<String, String> map, Object o) {
        return userRepository.emailLogin(map);
    }
}
