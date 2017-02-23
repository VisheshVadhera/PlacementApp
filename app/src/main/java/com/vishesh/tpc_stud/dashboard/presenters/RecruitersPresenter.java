package com.vishesh.tpc_stud.dashboard.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.dashboard.models.Job;
import com.vishesh.tpc_stud.dashboard.useCases.GetRecruitersUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class RecruitersPresenter extends BasePresenter {

    private RecruitersView recruitersView;

    private final GetRecruitersUseCase getRecruitersUseCase;

    @Inject
    public RecruitersPresenter(GetRecruitersUseCase getRecruitersUseCase) {
        this.getRecruitersUseCase = getRecruitersUseCase;
    }


    public void setView(RecruitersView recruitersView) {
        this.recruitersView = recruitersView;
    }


    public void onStart() {
        recruitersView.showLoader();
        Integer userId = 1;
        getRecruitersUseCase.execute(new JobsObserver(), userId, null);
    }

    public interface RecruitersView extends BaseView {

        void showJobs(List<Job> value);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        recruitersView = null;
    }

    private final class JobsObserver extends DisposableSingleObserver<List<Job>> {

        @Override
        public void onSuccess(List<Job> value) {
            recruitersView.hideLoader();
            recruitersView.showJobs(value);
        }

        @Override
        public void onError(Throwable e) {
            recruitersView.hideLoader();
            handleError(e);
        }
    }
}
