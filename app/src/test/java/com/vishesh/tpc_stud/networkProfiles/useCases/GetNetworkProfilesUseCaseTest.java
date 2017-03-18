package com.vishesh.tpc_stud.networkProfiles.useCases;

import com.vishesh.tpc_stud.core.repos.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Scheduler;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetNetworkProfilesUseCaseTest {

    private GetNetworkProfilesUseCase getNetworkProfilesUseCase;

    @Mock
    private Scheduler jobScheduler;
    @Mock
    private Scheduler postJobScheduler;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        getNetworkProfilesUseCase = new GetNetworkProfilesUseCase(jobScheduler,
                postJobScheduler, userRepository);
    }

    @Test
    public void testGetNetworkProfilesUseCaseObservable() {

        Integer userId = anyInt();
        Object o = new Object();

        getNetworkProfilesUseCase.buildObservable(userId, o);

        verify(userRepository).getNetworkProfiles(userId);
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }

}
