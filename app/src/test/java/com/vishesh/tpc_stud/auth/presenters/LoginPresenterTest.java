package com.vishesh.tpc_stud.auth.presenters;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.vishesh.tpc_stud.auth.constants.AuthConstants;
import com.vishesh.tpc_stud.auth.useCases.LoginUseCase;
import com.vishesh.tpc_stud.core.repos.LocalCache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private LoginUseCase loginUseCase;
    @Mock
    private LoginPresenter.LoginView loginView;
    @Mock
    private LocalCache localCache;

    private LoginPresenter loginPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        loginPresenter = new LoginPresenter(loginUseCase, localCache);
        loginPresenter.setView(loginView);
    }

    @Test
    public void clickOnLogin_showsAccountKitActivity() {
        loginPresenter.onEmailLoginClicked();

        verify(loginView).startLoginProcess(any(AccountKitConfiguration.class));
    }

    @Test
    public void onAccountKitLoginError_showErrorMessage() {

        AccountKitLoginResult accountKitLoginResult = new AccountKitLoginResult() {
            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {

            }

            @Nullable
            @Override
            public AccessToken getAccessToken() {
                return null;
            }

            @Nullable
            @Override
            public String getAuthorizationCode() {
                return null;
            }

            @Nullable
            @Override
            public AccountKitError getError() {
                return new AccountKitError(AccountKitError.Type.INTERNAL_ERROR);
            }

            @Nullable
            @Override
            public String getFinalAuthorizationState() {
                return null;
            }

            @Override
            public long getTokenRefreshIntervalInSeconds() {
                return 0;
            }

            @Override
            public boolean wasCancelled() {
                return false;
            }
        };

        loginPresenter.onEmailLoginResultReceived(accountKitLoginResult);
        verify(loginView).showMessage(accountKitLoginResult.getError().getErrorType().getMessage());
    }

    @Test
    public void onAccountKitLoginCancelled_showCancelledMessage() {

        AccountKitLoginResult accountKitLoginResult = new AccountKitLoginResult() {
            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {

            }

            @Nullable
            @Override
            public AccessToken getAccessToken() {
                return null;
            }

            @Nullable
            @Override
            public String getAuthorizationCode() {
                return null;
            }

            @Nullable
            @Override
            public AccountKitError getError() {
                return null;
            }

            @Nullable
            @Override
            public String getFinalAuthorizationState() {
                return null;
            }

            @Override
            public long getTokenRefreshIntervalInSeconds() {
                return 0;
            }

            @Override
            public boolean wasCancelled() {
                return true;
            }
        };

        loginPresenter.onEmailLoginResultReceived(accountKitLoginResult);
        verify(loginView).showMessage(AuthConstants.LOGIN_CANCELLED);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onAccountKitSuccessfulLogin_loginToRepository() {

        AccountKitLoginResult accountKitLoginResult = new AccountKitLoginResult() {
            @Nullable
            @Override
            public AccessToken getAccessToken() {
                return null;
            }

            @Nullable
            @Override
            public String getAuthorizationCode() {
                return "abc";
            }

            @Nullable
            @Override
            public AccountKitError getError() {
                return null;
            }

            @Nullable
            @Override
            public String getFinalAuthorizationState() {
                return null;
            }

            @Override
            public long getTokenRefreshIntervalInSeconds() {
                return 0;
            }

            @Override
            public boolean wasCancelled() {
                return false;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {

            }
        };

        loginPresenter.onEmailLoginResultReceived(accountKitLoginResult);

        verify(loginView).showLoader();
        verify(loginUseCase).execute(any(DisposableSingleObserver.class), any(Map.class), any(Void.class));
    }

}
