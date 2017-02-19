package com.vishesh.tpc_stud.auth.useCases;

import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.core.repos.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by vishesh on 18/2/17.
 */
public class AccessTokenUseCase extends BaseUseCase<Object, AccessToken>{

    private final UserRepository userRepository;

    @Inject
    public AccessTokenUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                              @Named("postJobScheduler") Scheduler postJobScheduler,
                              CompositeDisposable compositeDisposable,
                              UserRepository userRepository) {
        super(jobScheduler, postJobScheduler, compositeDisposable);
        this.userRepository = userRepository;
    }

    @Override
    protected Single<Object> buildObservable(AccessToken params) {
        return userRepository.saveAccessToken(params);
    }
}
