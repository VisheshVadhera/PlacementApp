package com.vishesh.tpc_stud.dashboard.presenters;

import com.vishesh.tpc_stud.auth.useCases.GetCurrentUserUseCase;
import com.vishesh.tpc_stud.auth.useCases.UpdateUserUseCase;
import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.dashboard.useCases.LogoutUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DashboardPresenterTest {

    @Mock
    private LogoutUseCase logoutUseCase;
    @Mock
    private GetCurrentUserUseCase getCurrentUserUseCase;
    @Mock
    private UpdateUserUseCase updateUserUseCase;
    @Mock
    private LocalCache localCache;
    @Mock
    private DashboardPresenter.DashboardView dashboardView;

    private DashboardPresenter dashboardPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        dashboardPresenter = new DashboardPresenter(localCache,
                logoutUseCase, getCurrentUserUseCase, updateUserUseCase);
        dashboardPresenter.setView(dashboardView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onStart_getCurrentUser() {
        dashboardPresenter.initialize();

        verify(dashboardView).showLoader();

        verify(getCurrentUserUseCase).execute(any(DisposableSingleObserver.class),
                any(Void.class), any(Void.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onLogoutClicked_logoutToRepository() {
        dashboardPresenter.onLogoutClicked();

        verify(dashboardView).showLoader();
        verify(logoutUseCase).execute(any(DisposableSingleObserver.class),
                any(Void.class), any(Void.class));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onUserNameReceived_updateUserToRepository() {

        String firstName = "Abc";
        String lastName = "Xyz";

        dashboardPresenter.onUserNameReceived(firstName, lastName);

        verify(dashboardView).showLoader();
        verify(updateUserUseCase).execute(any(DisposableSingleObserver.class),
                any(Integer.class), any(User.class));
    }
}
