package com.vishesh.placement.semesterGrades.useCases;

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
public class GetSemesterGradesUseCaseTest {

    private GetSemesterGradesUseCase getSemesterGradesUseCase;

    @Mock
    private Scheduler jobScheduler;
    @Mock
    private Scheduler postJobScheduler;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        getSemesterGradesUseCase = new GetSemesterGradesUseCase(jobScheduler,
                postJobScheduler, userRepository);
    }

    @Test
    public void testGetGpaUseCaseSingle() throws Exception {

        Integer userId = anyInt();

        getSemesterGradesUseCase.buildSingle(userId, new Object());

        verify(userRepository).getSemesterGrades(userId);
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }

}