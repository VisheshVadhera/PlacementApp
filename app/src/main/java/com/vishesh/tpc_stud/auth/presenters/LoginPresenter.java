package com.vishesh.tpc_stud.auth.presenters;

import android.text.TextUtils;

import com.facebook.accountkit.AccountKitLoginResult;
import com.fernandocejas.arrow.optional.Optional;
import com.vishesh.tpc_stud.auth.models.AccessToken;
import com.vishesh.tpc_stud.auth.useCases.GetCurrentUserUseCase;
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
    private final UpdateUserUseCase updateUserUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    private Optional<User> userOptional = Optional.absent();

    @Inject
    public LoginPresenter(LoginUseCase loginUseCase,
                          UpdateUserUseCase updateUserUseCase,
                          GetCurrentUserUseCase getCurrentUserUseCase) {
        this.loginUseCase = loginUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
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

    public void onUserNameReceived(String firstName, String lastName) {
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            loginView.showLoader();
            updateUserUseCase.execute(new UpdatedUserObserver(), user.getId(), user);
        } else {
            loginView.showMessage("Unable to perform request. Please try again");
        }
    }

    @Override
    public void destroy() {
        loginUseCase.dispose();
        updateUserUseCase.dispose();
        loginView = null;
    }

    public interface LoginView extends BaseView {

        void takeUserName();

        void openDashboard();
    }

    private final class LoginObserver extends DisposableSingleObserver<AccessToken> {

        @Override
        public void onSuccess(AccessToken value) {
            getCurrentUserUseCase.execute(new CurrentUserObserver(), null, null);
        }

        @Override
        public void onError(Throwable e) {
            loginView.hideLoader();
            handleError(e);
        }
    }

    private final class UpdatedUserObserver extends DisposableSingleObserver<User> {

        @Override
        public void onSuccess(User value) {
            loginView.hideLoader();
            loginView.openDashboard();
        }

        @Override
        public void onError(Throwable e) {
            loginView.hideLoader();
            handleError(e);
        }
    }

    private final class CurrentUserObserver extends DisposableSingleObserver<User> {

        @Override
        public void onSuccess(User user) {
            userOptional = Optional.of(user);

            loginView.hideLoader();
            if (TextUtils.isEmpty(user.getFirstName())) {
                loginView.takeUserName();
            } else {
                loginView.openDashboard();
            }
        }

        @Override
        public void onError(Throwable e) {
            loginView.hideLoader();
            handleError(e);
        }
    }
}
