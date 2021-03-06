package com.vishesh.placement.dashboard.useCases;

import com.vishesh.placement.dashboard.repos.RecruiterRepository;

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
public class GetRecruitersUseCaseTest {

    private GetRecruitersUseCase getRecruitersUseCase;

    @Mock
    private Scheduler jobScheduler;
    @Mock
    private Scheduler postJobScheduler;
    @Mock
    private RecruiterRepository recruiterRepository;

    @Before
    public void setup() {
        getRecruitersUseCase = new GetRecruitersUseCase(
                jobScheduler, postJobScheduler, recruiterRepository
        );
    }

    @Test
    public void testGetRecruiterUseCaseSingle() {
        Integer userId = anyInt();
        Object o = new Object();

        getRecruitersUseCase.buildSingle(userId, o);

        verify(recruiterRepository).getRecruiters(userId);
        verifyNoMoreInteractions(recruiterRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }
}
