package com.vishesh.tpc_stud.networkProfiles.useCases;

import com.vishesh.tpc_stud.common.repos.UserRepository;
import com.vishesh.tpc_stud.networkProfiles.models.Network;
import com.vishesh.tpc_stud.networkProfiles.models.NetworkProfile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class SaveNetworkProfileUseCaseTest {

    private SaveNetworkProfileUseCase saveNetworkProfileUseCase;

    @Mock
    private Scheduler jobScheduler;
    @Mock
    private Scheduler postJobScheduler;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        saveNetworkProfileUseCase = new SaveNetworkProfileUseCase(jobScheduler,
                postJobScheduler, userRepository);
    }

    @Test
    public void testSaveNetworkProfileUseCaseSingle() throws Exception {

        Integer userId = 1;

        NetworkProfile networkProfile = new NetworkProfile();
        networkProfile.setNetwork(Network.GITHUB);
        networkProfile.setUrl("fakeUrl");

        saveNetworkProfileUseCase.buildSingle(userId, networkProfile);

        verify(userRepository).saveNetworkProfile(userId, networkProfile);
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }
}