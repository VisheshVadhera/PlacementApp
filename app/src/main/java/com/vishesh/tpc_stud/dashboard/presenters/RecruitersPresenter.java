package com.vishesh.tpc_stud.dashboard.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.dashboard.models.Recruiter;
import com.vishesh.tpc_stud.dashboard.useCases.GetRecruitersUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class RecruitersPresenter extends BasePresenter {

    private RecruitersView recruitersView;

    private final GetRecruitersUseCase getRecruitersUseCase;
    private final LocalCache localCache;

    @Inject
    public RecruitersPresenter(GetRecruitersUseCase getRecruitersUseCase,
                               LocalCache localCache) {
        this.getRecruitersUseCase = getRecruitersUseCase;
        this.localCache = localCache;
    }

    public void setView(RecruitersView recruitersView) {
        this.recruitersView = recruitersView;
    }

    public void onStart() {
        recruitersView.showLoader();
        Integer userId = localCache.getUserId();
        getRecruitersUseCase.execute(new JobsObserver(), userId, null);
    }

    public interface RecruitersView extends BaseView {

        void showJobOffers(List<Recruiter> value);
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

    private final class JobsObserver extends DisposableSingleObserver<List<Recruiter>> {

        @Override
        public void onSuccess(List<Recruiter> value) {
            recruitersView.hideLoader();
            recruitersView.showJobOffers(value);
        }

        @Override
        public void onError(Throwable e) {
            recruitersView.hideLoader();
            handleError(e);
        }
    }
}
