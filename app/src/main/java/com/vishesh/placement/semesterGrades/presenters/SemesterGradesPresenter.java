package com.vishesh.placement.semesterGrades.presenters;

import com.vishesh.placement.core.presenters.BasePresenter;
import com.vishesh.placement.core.cache.LocalCache;
import com.vishesh.placement.core.views.BaseView;
import com.vishesh.placement.semesterGrades.models.SemesterGrade;
import com.vishesh.placement.semesterGrades.useCases.GetSemesterGradesUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class SemesterGradesPresenter extends BasePresenter {

    private SemesterGradesView semesterGradesView;

    private final LocalCache localCache;
    private final GetSemesterGradesUseCase getSemesterGradesUseCase;

    @Inject
    public SemesterGradesPresenter(LocalCache localCache,
                                   GetSemesterGradesUseCase getSemesterGradesUseCase) {
        this.localCache = localCache;
        this.getSemesterGradesUseCase = getSemesterGradesUseCase;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getSemesterGradesUseCase.dispose();
        semesterGradesView = null;
    }

    public void setView(SemesterGradesView semesterGradesView) {
        this.semesterGradesView = semesterGradesView;
    }

    public void initialize() {
        semesterGradesView.showLoader();

        getSemesterGradesUseCase.execute(new SemesterGradesObserver(), localCache.getUserId(), new Object());
    }

    public interface SemesterGradesView extends BaseView {

        void showSemesterGrades(List<SemesterGrade> semesterGrades);
    }

    private final class SemesterGradesObserver extends DisposableSingleObserver<List<SemesterGrade>> {

        @Override
        public void onSuccess(List<SemesterGrade> semesterGrades) {
            semesterGradesView.hideLoader();
            semesterGradesView.showSemesterGrades(semesterGrades);
        }

        @Override
        public void onError(Throwable e) {
            semesterGradesView.hideLoader();
            handleError(e);
        }
    }
}
