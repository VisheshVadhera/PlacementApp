package com.vishesh.tpc_stud.dashboard.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.dashboard.mappers.RecruiterModelMapper;
import com.vishesh.tpc_stud.dashboard.models.Recruiter;
import com.vishesh.tpc_stud.dashboard.models.RecruiterModel;
import com.vishesh.tpc_stud.dashboard.useCases.GetRecruitersUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class RecruitersPresenter extends BasePresenter {

    private RecruitersView recruitersView;

    private final GetRecruitersUseCase getRecruitersUseCase;
    private final LocalCache localCache;
    private final RecruiterModelMapper recruiterModelMapper;

    @Inject
    public RecruitersPresenter(GetRecruitersUseCase getRecruitersUseCase,
                               LocalCache localCache,
                               RecruiterModelMapper recruiterModelMapper) {
        this.getRecruitersUseCase = getRecruitersUseCase;
        this.localCache = localCache;
        this.recruiterModelMapper = recruiterModelMapper;
    }

    public void setView(RecruitersView recruitersView) {
        this.recruitersView = recruitersView;
    }

    public void onStart() {
        recruitersView.showLoader();
        Integer userId = localCache.getUserId();
        getRecruitersUseCase.execute(new JobOffersObserver(), userId, null);
    }

    public interface RecruitersView extends BaseView {

        void showJobOffers(List<RecruiterModel> recruiterModels);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getRecruitersUseCase.dispose();
        recruitersView = null;
    }

    private final class JobOffersObserver extends DisposableSingleObserver<List<Recruiter>> {

        @Override
        public void onSuccess(List<Recruiter> recruiters) {
            recruitersView.hideLoader();
            recruitersView.showJobOffers(recruiterModelMapper.transform(recruiters));
        }

        @Override
        public void onError(Throwable e) {
            recruitersView.hideLoader();
            handleError(e);
        }
    }
}
