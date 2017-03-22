package com.vishesh.tpc_stud.gpa.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.gpa.models.Gpa;
import com.vishesh.tpc_stud.gpa.useCases.GetGpaUseCase;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class GpaPresenter extends BasePresenter {

    private GpaView gpaView;

    private final LocalCache localCache;
    private final GetGpaUseCase getGpaUseCase;

    @Inject
    public GpaPresenter(LocalCache localCache, GetGpaUseCase getGpaUseCase) {
        this.localCache = localCache;
        this.getGpaUseCase = getGpaUseCase;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void setView(GpaView gpaView) {
        this.gpaView = gpaView;
    }

    public void onStart() {
        gpaView.showLoader();

        getGpaUseCase.execute(new GpaObserver(), localCache.getUserId(), new Object());
    }

    public interface GpaView extends BaseView {

        void showGpa(Gpa gpa);
    }

    private final class GpaObserver extends DisposableSingleObserver<Gpa> {

        @Override
        public void onSuccess(Gpa gpa) {
            gpaView.hideLoader();
            gpaView.showGpa(gpa);
        }

        @Override
        public void onError(Throwable e) {
            gpaView.hideLoader();
            handleError(e);
        }
    }
}
