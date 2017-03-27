package com.vishesh.tpc_stud.core.useCases;

import com.vishesh.tpc_stud.core.idlingResources.EspressoIdlingResource;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;

public abstract class BaseUseCase<Output, Input1, Input2> {

    private final Scheduler jobScheduler;
    private final Scheduler postJobScheduler;
    private final CompositeDisposable compositeDisposable;

    public BaseUseCase(Scheduler jobScheduler,
                       Scheduler postJobScheduler) {
        this.jobScheduler = jobScheduler;
        this.postJobScheduler = postJobScheduler;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void execute(DisposableSingleObserver<Output> disposableObserver,
                        Input1 input1, Input2 input2) {
        Single<Output> single = buildSingle(input1, input2)
                .subscribeOn(jobScheduler)
                .observeOn(postJobScheduler)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        EspressoIdlingResource.increment();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        EspressoIdlingResource.decrement();
                    }
                });
        addDisposable(single.subscribeWith(disposableObserver));
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public abstract Single<Output> buildSingle(Input1 input1, Input2 input2);

    private void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
