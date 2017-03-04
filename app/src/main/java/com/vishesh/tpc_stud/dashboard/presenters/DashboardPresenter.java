package com.vishesh.tpc_stud.dashboard.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.dashboard.useCases.LogoutUseCase;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class DashboardPresenter extends BasePresenter {

    private DashboardView dashboardView;

    private final LocalCache localCache;
    private final LogoutUseCase logoutUseCase;

    @Inject
    public DashboardPresenter(LocalCache localCache, LogoutUseCase logoutUseCase) {
        this.localCache = localCache;
        this.logoutUseCase = logoutUseCase;
    }

    public void setView(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
    }

    public void onLogoutClicked() {
        dashboardView.showLoader();
        logoutUseCase.execute(new LogoutObserver(), null, null);
    }

    public interface DashboardView extends BaseView {

        void openLoginScreen();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        logoutUseCase.dispose();
        dashboardView = null;
    }

    private class LogoutObserver extends DisposableSingleObserver<Object> {

        @Override
        public void onSuccess(Object value) {
            localCache.deleteUserId();
            localCache.deleteAccessToken();

            dashboardView.hideLoader();
            dashboardView.openLoginScreen();
        }

        @Override
        public void onError(Throwable e) {
            dashboardView.hideLoader();
            handleError(e);
        }
    }
}
