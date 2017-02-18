package com.vishesh.tpc_stud.auth.useCases;

import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.services.AuthService;
import com.vishesh.tpc_stud.core.helpers.BaseUseCase;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by vishesh on 14/2/17.
 */
public class LoginUseCase extends BaseUseCase<AccessToken, Map<String, String>> {

    private final AuthService authService;

    @Inject
    protected LoginUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                           @Named("postJobScheduler") Scheduler postJobScheduler,
                           CompositeDisposable compositeDisposable,
                           AuthService authService) {
        super(jobScheduler, postJobScheduler, compositeDisposable);
        this.authService = authService;
    }

    @Override
    protected Single<AccessToken> buildObservable(Map<String, String> params) {
        return authService.emailLogin(params);
    }
}
