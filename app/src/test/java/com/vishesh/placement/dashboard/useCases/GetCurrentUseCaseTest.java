package com.vishesh.placement.dashboard.useCases;

import com.vishesh.placement.common.repos.UserRepository;

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
public class GetCurrentUseCaseTest {

    private GetCurrentUserUseCase getCurrentUserUseCase;

    @Mock
    private Scheduler jobScheduler;
    @Mock
    private Scheduler postJobScheduler;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        getCurrentUserUseCase = new GetCurrentUserUseCase(jobScheduler,
                postJobScheduler, userRepository);
    }

    @Test
    public void testGetCurrentUseCaseSingle() {
        Object o = new Object();
        Object o2 = new Object();

        getCurrentUserUseCase.buildSingle(o, o2);

        verify(userRepository).getCurrentUser();
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }
}
