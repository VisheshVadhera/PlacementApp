package com.vishesh.tpc_stud.networkProfiles.presenters;

import com.vishesh.tpc_stud.networkProfiles.useCases.GetNetworkProfilesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NetworkProfilesPresenterTest {

    private NetworkProfilesPresenter networkProfilesPresenter;

    @Mock
    private NetworkProfilesPresenter.NetworkProfilesView networkProfilesView;

    @Mock
    private GetNetworkProfilesUseCase getNetworkProfilesUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        networkProfilesPresenter = new NetworkProfilesPresenter(getNetworkProfilesUseCase);

    }

    @Test
    public void onStart_getNetworkProfiles() {
        networkProfilesPresenter.onStart();

        verify(networkProfilesView).showLoader();

    }

}
