package com.vishesh.tpc_stud.core;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by vishesh on 14/2/17.
 */
public abstract class BaseUseCase<T, Parameters> {

    private final Scheduler jobScheduler;
    private final Scheduler postJobScheduler;
    private final CompositeDisposable compositeDisposable;

    protected BaseUseCase(Scheduler jobScheduler,
                          Scheduler postJobScheduler,
                          CompositeDisposable compositeDisposable) {
        this.jobScheduler = jobScheduler;
        this.postJobScheduler = postJobScheduler;
        this.compositeDisposable = compositeDisposable;
    }

    public void execute(DisposableSingleObserver<T> disposableObserver, Parameters params) {
        Single<T> single = buildObservable(params)
                .subscribeOn(jobScheduler)
                .observeOn(postJobScheduler);
        addDisposable(single.subscribeWith(disposableObserver));
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Single<T> buildObservable(Parameters params);

    private void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
