package com.vishesh.tpc_stud.core.helpers;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by vishesh on 14/2/17.
 */
public abstract class BaseUseCase<Output, Input1, Input2> {

    private final Scheduler jobScheduler;
    private final Scheduler postJobScheduler;
    private final CompositeDisposable compositeDisposable;

    protected BaseUseCase(Scheduler jobScheduler,
                          Scheduler postJobScheduler) {
        this.jobScheduler = jobScheduler;
        this.postJobScheduler = postJobScheduler;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void execute(DisposableSingleObserver<Output> disposableObserver,
                        Input1 input1, Input2 input2) {
        Single<Output> single = buildObservable(input1, input2)
                .subscribeOn(jobScheduler)
                .observeOn(postJobScheduler);
        addDisposable(single.subscribeWith(disposableObserver));

    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Single<Output> buildObservable(Input1 input1, Input2 input2);

    private void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
