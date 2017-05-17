package com.vishesh.placement.dashboard.useCases;

import com.vishesh.placement.common.repos.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Scheduler;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetProfileUseCaseTest {

    private GetProfileUseCase getProfileUseCase;

    @Mock
    private Scheduler jobScheduler;
    @Mock
    private Scheduler postJobScheduler;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        getProfileUseCase = new GetProfileUseCase(jobScheduler,
                postJobScheduler, userRepository);
    }

    @Test
    public void testGetProfileUseCaseSingle() {
        Integer userId = anyInt();
        Object o = new Object();

        getProfileUseCase.buildSingle(userId, o);

        verify(userRepository).getProfile(userId);
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }

}
