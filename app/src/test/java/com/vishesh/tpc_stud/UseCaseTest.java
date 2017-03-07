package com.vishesh.tpc_stud;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.TestScheduler;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseTest {

    private UseCaseTestClass useCase;

    private TestDisposableObserver<Object> observer;

    @Mock
    private Scheduler mockJobScheduler;

    private Scheduler mockPostJobScheduler = new TestScheduler();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        useCase = new UseCaseTestClass(mockJobScheduler,
                mockPostJobScheduler);
        observer = new TestDisposableObserver<>();
    }

    @Test()
    public void onNullObserverPassed_useCaseShouldFail() {
        expectedException.expect(NullPointerException.class);
        Object o = new Object();
        useCase.execute(null, o, o);
    }

    @Test
    public void afterSuccessfulExecution_useCaseShouldBeDisposed() {
        Object o = new Object();

        useCase.execute(observer, o, o);
        useCase.dispose();

        Assertions.assertThat(observer.isDisposed())
                .isTrue();
    }

    private static class UseCaseTestClass extends BaseUseCase<Object, Object, Object> {

        UseCaseTestClass(Scheduler jobScheduler,
                         Scheduler postJobScheduler) {
            super(jobScheduler, postJobScheduler);
        }

        @Override
        protected Single<Object> buildObservable(Object o, Object o2) {
            return Single.just(new Object());
        }

        @Override
        public void execute(DisposableSingleObserver<Object> disposableObserver, Object o, Object o2) {
            super.execute(disposableObserver, o, o2);
        }
    }


    private static class TestDisposableObserver<T> extends DisposableSingleObserver<T> {

        private int valuesCount = 0;

        @Override
        public void onSuccess(T value) {
            valuesCount++;
        }

        @Override
        public void onError(Throwable e) {

        }
    }
}
