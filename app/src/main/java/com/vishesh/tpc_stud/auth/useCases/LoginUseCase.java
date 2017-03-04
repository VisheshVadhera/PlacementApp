package com.vishesh.tpc_stud.auth.useCases;

import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.core.repos.UserRepository;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by vishesh on 14/2/17.
 */
public class LoginUseCase extends BaseUseCase<AccessToken, Map<String, String>, Void> {

    private final UserRepository userRepository;

    @Inject
    protected LoginUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                           @Named("postJobScheduler") Scheduler postJobScheduler,
                           CompositeDisposable compositeDisposable,
                           UserRepository userRepository) {
        super(jobScheduler, postJobScheduler, compositeDisposable);
        this.userRepository = userRepository;
    }

    @Override
    protected Single<AccessToken> buildObservable(Map<String, String> map, Void aVoid) {
        return userRepository.emailLogin(map);
    }
}
