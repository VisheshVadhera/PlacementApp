package com.vishesh.tpc_stud.gpa.useCases;

import com.vishesh.tpc_stud.core.repos.UserRepository;

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
public class GetGpaUseCaseTest {

    private GetGpaUseCase getGpaUseCase;

    @Mock
    private Scheduler jobScheduler;
    @Mock
    private Scheduler postJobScheduler;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        getGpaUseCase = new GetGpaUseCase(jobScheduler,
                postJobScheduler, userRepository);
    }

    @Test
    public void testGetGpaUseCaseSingle() throws Exception {

        Integer userId = anyInt();

        getGpaUseCase.buildSingle(userId, new Object());

        verify(userRepository).getGpa(userId);
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }

}