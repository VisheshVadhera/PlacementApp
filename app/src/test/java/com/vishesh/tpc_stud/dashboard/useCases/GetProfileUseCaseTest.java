package com.vishesh.tpc_stud.dashboard.useCases;

import com.vishesh.tpc_stud.core.repos.UserRepository;
import com.vishesh.tpc_stud.dashboard.useCases.GetProfileUseCase;

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
    public void testGetProfileUseCaseObservable() {
        Integer userId = anyInt();
        Object o = new Object();

        getProfileUseCase.buildObservable(userId, o);

        verify(userRepository).getProfile(userId);
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }

}
