package com.vishesh.tpc_stud.auth;

import com.vishesh.tpc_stud.auth.useCases.GetCurrentUserUseCase;
import com.vishesh.tpc_stud.core.repos.UserRepository;

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
    public void testGetCurrentUseCaseObservable() {
        Object o = new Object();
        Object o2 = new Object();

        getCurrentUserUseCase.buildObservable(o, o2);

        verify(userRepository).getCurrentUser();
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }
}
