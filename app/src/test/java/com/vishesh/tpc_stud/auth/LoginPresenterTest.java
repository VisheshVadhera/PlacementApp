package com.vishesh.tpc_stud.auth;

import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.vishesh.tpc_stud.auth.presenters.LoginPresenter;
import com.vishesh.tpc_stud.auth.useCases.LoginUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private LoginUseCase loginUseCase;
    @Mock
    private LoginPresenter.LoginView loginView;

    private LoginPresenter loginPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        loginPresenter = new LoginPresenter(loginUseCase);
        loginPresenter.setView(loginView);
    }

    @Test
    public void clickOnLogin_showsAccountKitActivity() {
        loginPresenter.onEmailLoginClicked();

        verify(loginView).startLoginProcess(any(AccountKitConfiguration.class));
    }
}
