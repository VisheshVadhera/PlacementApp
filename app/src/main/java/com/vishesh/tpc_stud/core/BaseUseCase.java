package com.vishesh.tpc_stud.core;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by vishesh on 14/2/17.
 */
public abstract class BaseUseCase<T, Parameters> {

    private final Scheduler jobScheduler;
    private final Scheduler postJobScheduler;
    private final CompositeDisposable compositeDisposable;

    @Inject
    protected BaseUseCase(Scheduler jobScheduler,
                          Scheduler postJobScheduler,
                          CompositeDisposable compositeDisposable) {
        this.jobScheduler = jobScheduler;
        this.postJobScheduler = postJobScheduler;
        this.compositeDisposable = compositeDisposable;
    }

    public void execute(DisposableObserver<T> disposableObserver, Parameters params) {
        Observable<T> observable = buildObservable(params)
                .subscribeOn(jobScheduler)
                .observeOn(postJobScheduler);
        addDisposable(observable.subscribeWith(disposableObserver));
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Observable<T> buildObservable(Parameters params);

    private void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
