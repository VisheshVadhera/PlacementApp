package com.vishesh.tpc_stud.auth.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.core.repos.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;


public class FetchCurrentUserUseCase extends BaseUseCase<User, Void, Void>{

    private final UserRepository userRepository;

    @Inject
    protected FetchCurrentUserUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                                      @Named("postJobScheduler") Scheduler postJobScheduler,
                                      CompositeDisposable compositeDisposable,
                                      UserRepository userRepository) {
        super(jobScheduler, postJobScheduler, compositeDisposable);
        this.userRepository = userRepository;
    }

    @Override
    protected Single<User> buildObservable(Void aVoid, Void bVoid) {
        return userRepository.getCurrentUser();
    }
}
