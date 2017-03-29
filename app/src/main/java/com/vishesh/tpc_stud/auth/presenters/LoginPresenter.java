package com.vishesh.tpc_stud.auth.presenters;

import com.vishesh.tpc_stud.auth.constants.AuthConstants;
import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.useCases.LoginUseCase;
import com.vishesh.tpc_stud.core.cache.LocalCache;
import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.views.BaseView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class LoginPresenter extends BasePresenter {

    private static final String AUTHORIZATION_CODE_KEY = "authorizationCode";

    private LoginView loginView;

    private final LoginUseCase loginUseCase;
    private final LocalCache localCache;

    @Inject
    public LoginPresenter(LoginUseCase loginUseCase, LocalCache localCache) {
        this.loginUseCase = loginUseCase;
        this.localCache = localCache;
    }

    public void setView(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    public void onEmailLoginClicked() {
        loginView.startLoginProcess();
    }

    @Override
    public void destroy() {
        loginUseCase.dispose();
    }

    @Override
    public void unsetView() {
        loginView = null;
    }

    public void onAccountKitLoginError() {
        loginView.showMessage(AuthConstants.ACCOUNT_KIT_ERROR_MSG);
    }

    public void onAccountKitLoginCancelled() {
        loginView.showMessage(AuthConstants.LOGIN_CANCELLED_MSG);
    }

    public void onAccountKitLoginResult(String authorizationCode) {
        Map<String, String> map = new HashMap<>();
        map.put(AUTHORIZATION_CODE_KEY, authorizationCode);
        loginView.showLoader();
        loginUseCase.execute(new LoginPresenter.LoginObserver(), map, new Object());
    }

    public interface LoginView extends BaseView {

        void openDashboard();

        void startLoginProcess();

    }

    private final class LoginObserver extends DisposableSingleObserver<AccessToken> {

        @Override
        public void onSuccess(AccessToken value) {
            if (loginView != null) {
                localCache.saveAccessToken(value.getAccessToken());
                loginView.hideLoader();
                loginView.openDashboard();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (loginView != null) {
                loginView.hideLoader();
                handleError(e);
            }
        }
    }
}
