package com.vishesh.tpc_stud.dashboard.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.dashboard.useCases.LogoutUseCase;

import javax.inject.Inject;

public class DashboardPresenter extends BasePresenter {

    private DashboardView dashboardView;

    private LogoutUseCase logoutUseCase;

    @Inject
    public DashboardPresenter(LogoutUseCase logoutUseCase) {
        this.logoutUseCase = logoutUseCase;
    }

    public void setView(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
    }

    public interface DashboardView extends BaseView {

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
}
