package com.vishesh.tpc_stud.auth.presenters;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.vishesh.tpc_stud.auth.constants.AuthConstants;
import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.useCases.LoginUseCase;
import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.cache.LocalCache;
import com.vishesh.tpc_stud.core.views.BaseView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

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

        if (accountKitLoginResult.getError() != null) {

            loginView.showMessage(AuthConstants.ACCOUNT_KIT_ERROR_MSG);

        } else if (accountKitLoginResult.wasCancelled()) {

            loginView.showMessage(AuthConstants.LOGIN_CANCELLED_MSG);

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
    }

    @Override
    public void unsetView() {
        loginView = null;
    }

    public interface LoginView extends BaseView {

        void openDashboard();

        void startLoginProcess(AccountKitConfiguration accountKitConfiguration);
    }

    private final class LoginObserver extends DisposableSingleObserver<AccessToken> {

        @Override
        public void onSuccess(AccessToken value) {
            if(loginView!=null){
                localCache.saveAccessToken(value.getAccessToken());
                loginView.hideLoader();
                loginView.openDashboard();
            }
        }

        @Override
        public void onError(Throwable e) {
            if(loginView!=null){
                loginView.hideLoader();
                handleError(e);
            }
        }
    }
}
