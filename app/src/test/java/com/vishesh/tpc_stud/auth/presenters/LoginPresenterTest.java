package com.vishesh.tpc_stud.auth.presenters;

import com.vishesh.tpc_stud.auth.constants.AuthConstants;
import com.vishesh.tpc_stud.auth.useCases.LoginUseCase;
import com.vishesh.tpc_stud.core.cache.LocalCache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMapOf;
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

        verify(loginView).startLoginProcess();
    }

    @Test
    public void onAccountKitLoginError_showErrorMessage() {

        loginPresenter.onAccountKitLoginError();

        verify(loginView).showMessage(AuthConstants.ACCOUNT_KIT_ERROR_MSG);
    }

    @Test
    public void onAccountKitLoginError_showCancelledMessage() {
        loginPresenter.onAccountKitLoginCancelled();

        verify(loginView).showMessage(AuthConstants.LOGIN_CANCELLED_MSG);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onSuccessfulLogin_login() {
        String authCode = "fakeAuthCode";

        loginPresenter.onAccountKitLoginResult(authCode);

        verify(loginView).showLoader();
        verify(loginUseCase).execute(any(DisposableSingleObserver.class),
                anyMapOf(String.class, String.class), any(Object.class));
    }

}
