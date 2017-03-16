package com.vishesh.tpc_stud.auth.presenters;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.vishesh.tpc_stud.auth.constants.AuthConstants;
import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.useCases.LoginUseCase;
import com.vishesh.tpc_stud.core.helpers.EspressoIdlingResource;
import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.repos.LocalCache;
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

    public void onEmailLoginResultReceived(AccountKitLoginResult accountKitLoginResult) {

        String message;

        if (accountKitLoginResult.getError() != null) {
            message = accountKitLoginResult.getError().getErrorType().getMessage();
            loginView.showMessage(message);
        } else if (accountKitLoginResult.wasCancelled()) {
            loginView.showMessage(AuthConstants.LOGIN_CANCELLED);
        } else if (accountKitLoginResult.getAuthorizationCode() != null) {
            Map<String, String> map = new HashMap<>();
            map.put("authorizationCode", accountKitLoginResult.getAuthorizationCode());
            loginView.showLoader();


            loginUseCase.execute(new LoginObserver(), map, null);
        }
    }

    public void onEmailLoginClicked() {
        AccountKitConfiguration accountKitConfiguration =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL,
                        AccountKitActivity.ResponseType.CODE).build();

        loginView.startLoginProcess(accountKitConfiguration);
    }

    @Override
    public void destroy() {
        loginUseCase.dispose();
        loginView = null;
    }

    public interface LoginView extends BaseView {

        void openDashboard();

        void startLoginProcess(AccountKitConfiguration accountKitConfiguration);
    }

    private final class LoginObserver extends DisposableSingleObserver<AccessToken> {

        @Override
        public void onSuccess(AccessToken value) {
            localCache.saveAccessToken(value.getAccessToken());
            loginView.hideLoader();
            loginView.openDashboard();
        }

        @Override
        public void onError(Throwable e) {
            loginView.hideLoader();
            handleError(e);
        }
    }
}
