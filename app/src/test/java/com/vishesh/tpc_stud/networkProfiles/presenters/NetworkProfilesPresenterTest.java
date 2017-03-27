package com.vishesh.tpc_stud.networkProfiles.presenters;

import com.vishesh.tpc_stud.core.cache.LocalCache;
import com.vishesh.tpc_stud.networkProfiles.models.Network;
import com.vishesh.tpc_stud.networkProfiles.models.NetworkProfile;
import com.vishesh.tpc_stud.networkProfiles.constants.NetworkProfileConstants;
import com.vishesh.tpc_stud.networkProfiles.useCases.GetNetworkProfilesUseCase;
import com.vishesh.tpc_stud.networkProfiles.useCases.SaveNetworkProfileUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NetworkProfilesPresenterTest {

    private NetworkProfilesPresenter networkProfilesPresenter;

    @Mock
    private NetworkProfilesPresenter.NetworkProfilesView networkProfilesView;
    @Mock
    private GetNetworkProfilesUseCase getNetworkProfilesUseCase;
    @Mock
    private SaveNetworkProfileUseCase saveNetworkProfileUseCase;
    @Mock
    private LocalCache localCache;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        networkProfilesPresenter = new NetworkProfilesPresenter(getNetworkProfilesUseCase,
                saveNetworkProfileUseCase,
                localCache);
        networkProfilesPresenter.setNetworkProfilesView(networkProfilesView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onStart_getNetworkProfiles() {
        networkProfilesPresenter.initialize();

        verify(networkProfilesView).showLoader();
        verify(getNetworkProfilesUseCase).execute(any(DisposableSingleObserver.class),
                anyInt(), any(Object.class));
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

    @Test
    public void onAddNetworkItemClicked_askForProfileUrl() {
        Network network = Network.GITHUB;

        networkProfilesPresenter.onAddNetworkProfileClicked(network);

        verify(networkProfilesView).askForProfileUrl(network);
    }

    @Test
    public void onNewNetworkProfileSaveClicked_invalidUrl_showMessage() {

        Network network = Network.GITHUB;
        String url = "httpssw://example.com";

        networkProfilesPresenter.onNewNetworkProfileSaveClicked(url, network);

        verify(networkProfilesView).showMessage(NetworkProfileConstants.INVALID_URL_MESSAGE);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onNewNetworkProfileSaveClicked_validUrl_saveNetworkProfile() {

        Network network = Network.GITHUB;
        String url = "https://example.com";

        networkProfilesPresenter.onNewNetworkProfileSaveClicked(url, network);

        verify(networkProfilesView).showLoader();
        verify(saveNetworkProfileUseCase).execute(any(DisposableSingleObserver.class),
                anyInt(), any(NetworkProfile.class));
    }
}
