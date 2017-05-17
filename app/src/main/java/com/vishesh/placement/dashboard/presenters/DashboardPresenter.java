package com.vishesh.placement.dashboard.presenters;

import android.text.TextUtils;

import com.vishesh.placement.dashboard.useCases.GetCurrentUserUseCase;
import com.vishesh.placement.dashboard.useCases.UpdateUserUseCase;
import com.vishesh.placement.common.models.User;
import com.vishesh.placement.core.presenters.BasePresenter;
import com.vishesh.placement.core.cache.LocalCache;
import com.vishesh.placement.core.views.BaseView;
import com.vishesh.placement.dashboard.useCases.LogoutUseCase;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class DashboardPresenter extends BasePresenter {

    private DashboardView dashboardView;

    private final LocalCache localCache;
    private final LogoutUseCase logoutUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Inject
    public DashboardPresenter(LocalCache localCache,
                              LogoutUseCase logoutUseCase,
                              GetCurrentUserUseCase getCurrentUserUseCase,
                              UpdateUserUseCase updateUserUseCase) {
        this.localCache = localCache;
        this.logoutUseCase = logoutUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    public void setView(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
    }

    public void initialize() {
        dashboardView.showLoader();

        getCurrentUserUseCase.execute(new CurrentUserObserver(), null, null);
    }

    public void onUserNameReceived(String firstName, String lastName) {

        User user = new User();
        user.setId(localCache.getUserId());
        user.setFirstName(firstName);
        user.setLastName(lastName);

        dashboardView.showLoader();

        updateUserUseCase.execute(new UpdatedUserObserver(), localCache.getUserId(), user);
    }

    public void onLogoutClicked() {
        dashboardView.showLoader();

        logoutUseCase.execute(new LogoutObserver(), null, null);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        getCurrentUserUseCase.dispose();
        updateUserUseCase.dispose();
        logoutUseCase.dispose();
        dashboardView = null;
    }

    public interface DashboardView extends BaseView {

        void openLoginScreen();

        void takeUserName();

        void setupTabs();
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

    private class CurrentUserObserver extends DisposableSingleObserver<User> {

        @Override
        public void onSuccess(User user) {


            localCache.saveUserId(user.getId());

            if (TextUtils.isEmpty(user.getFirstName())) {
                dashboardView.takeUserName();
            } else {
                dashboardView.hideLoader();
                dashboardView.setupTabs();
            }
        }

        @Override
        public void onError(Throwable e) {

            dashboardView.hideLoader();
            handleError(e);
        }
    }

    private class UpdatedUserObserver extends DisposableSingleObserver<User> {

        @Override
        public void onSuccess(User value) {

            dashboardView.hideLoader();
            dashboardView.setupTabs();
        }

        @Override
        public void onError(Throwable e) {

            dashboardView.hideLoader();
            handleError(e);
        }
    }
}
