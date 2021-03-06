package com.vishesh.placement.dashboard.presenters;

import com.vishesh.placement.core.presenters.BasePresenter;
import com.vishesh.placement.core.cache.LocalCache;
import com.vishesh.placement.core.views.BaseView;
import com.vishesh.placement.dashboard.mappers.RecruiterModelMapper;
import com.vishesh.placement.dashboard.models.Recruiter;
import com.vishesh.placement.dashboard.models.RecruiterModel;
import com.vishesh.placement.dashboard.useCases.GetRecruitersUseCase;

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

    public void initialize() {
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
