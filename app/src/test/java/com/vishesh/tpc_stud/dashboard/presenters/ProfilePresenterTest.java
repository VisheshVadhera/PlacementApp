package com.vishesh.tpc_stud.dashboard.presenters;

import com.vishesh.tpc_stud.auth.useCases.GetCurrentUserUseCase;
import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.dashboard.useCases.GetProfileUseCase;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProfilePresenterTest {

    @Mock
    private LocalCache localCache;
    @Mock
    private GetProfileUseCase getProfileUseCase;
    @Mock
    private GetCurrentUserUseCase getCurrentUserUseCase;
    @Mock
    private ProfilePresenter.ProfileView profileView;

    private ProfilePresenter profilePresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        profilePresenter = new ProfilePresenter(getProfileUseCase, getCurrentUserUseCase, localCache);
        profilePresenter.setProfileView(profileView);
    }

    @Ignore
    @SuppressWarnings("unchecked")
    public void onStart_getCurrentUser() {
        profilePresenter.onStart();

        verify(profileView).showLoader();
        verify(getCurrentUserUseCase).execute(any(DisposableSingleObserver.class),
                any(Void.class), any(Void.class));
    }

    @Test
    public void onNetworkProfileTapped_openNetworkProfilesScreen() {

        profilePresenter.onNetworkProfileTapped();

        verify(profileView).openNetworkProfilesScreen();
    }

    @Test
    public void onGpaTapped() throws Exception {

        profilePresenter.onGpaTapped();

        verify(profileView).openSemesterGradesScreen();
    }
}
