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
public class LogoutUseCaseTest {

    private LogoutUseCase logoutUseCase;

    @Mock
    private Scheduler jobScheduler;
    @Mock
    private Scheduler postJobScheduler;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        logoutUseCase = new LogoutUseCase(jobScheduler,
                postJobScheduler, userRepository);
    }

    @Test
    public void testLogoutUseCaseSingle(){
        Object o = new Object();
        Object o2 = new Object();

        logoutUseCase.buildSingle(o, o2);

        verify(userRepository).logout();
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }
}
