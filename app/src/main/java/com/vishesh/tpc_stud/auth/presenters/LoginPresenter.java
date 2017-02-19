package com.vishesh.tpc_stud.auth.presenters;

import com.facebook.accountkit.AccountKitLoginResult;
import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.useCases.AccessTokenUseCase;
import com.vishesh.tpc_stud.auth.useCases.LoginUseCase;
import com.vishesh.tpc_stud.auth.useCases.UpdateUserUseCase;
import com.vishesh.tpc_stud.core.models.User;
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
    private final AccessTokenUseCase accessTokenUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Inject
    public LoginPresenter(LoginUseCase loginUseCase,
                          AccessTokenUseCase accessTokenUseCase,
                          UpdateUserUseCase updateUserUseCase) {
        this.loginUseCase = loginUseCase;
        this.accessTokenUseCase = accessTokenUseCase;
        this.updateUserUseCase = updateUserUseCase;
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
            loginUseCase.execute(new LoginObserver(), map);
        }
    }

    public void onUserNameReceived(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        loginView.showLoader();
        updateUserUseCase.execute(new UserObserver(), user);
    }

    @Override
    public void destroy() {
        loginUseCase.dispose();
        loginView = null;
    }

    public interface LoginView extends BaseView {

        void takeUserName();

        void showDashboard();
    }

    private final class LoginObserver extends DisposableSingleObserver<AccessToken> {

        @Override
        public void onSuccess(AccessToken value) {
            accessTokenUseCase.execute(new AccessTokenObserver(), value);
        }

        @Override
        public void onError(Throwable e) {
            loginView.hideLoader();
            handleError(e);
        }
    }

    private final class AccessTokenObserver extends DisposableSingleObserver<Object> {

        @Override
        public void onSuccess(Object o) {
            loginView.hideLoader();
            loginView.takeUserName();
        }

        @Override
        public void onError(Throwable e) {
            loginView.hideLoader();
            handleError(e);
        }
    }


    private final class UserObserver extends DisposableSingleObserver<User> {

        @Override
        public void onSuccess(User value) {
            loginView.hideLoader();
            loginView.showDashboard();
        }

        @Override
        public void onError(Throwable e) {
            loginView.hideLoader();
            handleError(e);
        }
    }
}
