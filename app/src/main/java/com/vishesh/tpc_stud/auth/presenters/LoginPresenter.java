package com.vishesh.tpc_stud.auth.presenters;

import com.facebook.accountkit.AccountKitLoginResult;
import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.useCases.LoginUseCase;
import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.views.BaseView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by vishesh on 12/2/17.
 */
public class LoginPresenter extends BasePresenter {

    private LoginView loginView;

    private final LoginUseCase loginUseCase;

    @Inject
    public LoginPresenter(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
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

    public void onEmailLoginResultReceived(AccountKitLoginResult accountKitLoginResult) {

        String message;

        if (accountKitLoginResult.getError() != null) {
            message = accountKitLoginResult.getError().getErrorType().getMessage();
            loginView.showMessage(message);
        } else if (accountKitLoginResult.wasCancelled()) {
            message = "Login Cancelled";
            loginView.showMessage(message);
        } else if (accountKitLoginResult.getAuthorizationCode() != null) {
            Map<String, String> map = new HashMap<>();
            map.put("authorizationCode", accountKitLoginResult.getAuthorizationCode());
            loginView.showLoader();
            loginUseCase.execute(new LoginObserver(), map, null);
        }
    }

    @Override
    public void destroy() {
        loginUseCase.dispose();
        loginView = null;
    }

    public interface LoginView extends BaseView {

        void openDashboard();
    }

    private final class LoginObserver extends DisposableSingleObserver<AccessToken> {

        @Override
        public void onSuccess(AccessToken value) {
            loginView.openDashboard();
        }

        @Override
        public void onError(Throwable e) {
            loginView.hideLoader();
            handleError(e);
        }
    }

}
