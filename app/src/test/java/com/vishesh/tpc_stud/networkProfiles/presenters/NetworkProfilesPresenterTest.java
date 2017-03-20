package com.vishesh.tpc_stud.networkProfiles.presenters;

import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.dashboard.models.Network;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;
import com.vishesh.tpc_stud.networkProfiles.useCases.GetNetworkProfilesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NetworkProfilesPresenterTest {

    private NetworkProfilesPresenter networkProfilesPresenter;

    @Mock
    private NetworkProfilesPresenter.NetworkProfilesView networkProfilesView;
    @Mock
    private GetNetworkProfilesUseCase getNetworkProfilesUseCase;
    @Mock
    private LocalCache localCache;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        networkProfilesPresenter = new NetworkProfilesPresenter(getNetworkProfilesUseCase,
                localCache);
        networkProfilesPresenter.setNetworkProfilesView(networkProfilesView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onStart_getNetworkProfiles() {
        networkProfilesPresenter.onStart();

        verify(networkProfilesView).showLoader();
        verify(getNetworkProfilesUseCase).execute(any(DisposableSingleObserver.class),
                anyInt(), Mockito.any(Object.class));
    }

    @Test
    public void onNetworkItemClicked_openExternalUrl() {
        String fakeUrl = "fakeUrl";

        NetworkProfile networkProfile = new NetworkProfile();
        networkProfile.setNetwork(Network.GITHUB);
        networkProfile.setUrl(fakeUrl);

        networkProfilesPresenter.onNetworkProfileItemClicked(networkProfile);

        verify(networkProfilesView).openExternalLink(fakeUrl);
    }

}
