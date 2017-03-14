package com.vishesh.tpc_stud.dashboard.repos;

import com.vishesh.tpc_stud.dashboard.services.RecruiterService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RecruiterRepositoryTest {

    private RecruiterRepository recruiterRepository;

    @Mock
    private RecruiterService recruiterService;

    @Before
    public void setup() {
        recruiterRepository = new RecruiterRepository(recruiterService);
    }

    @Test
    public void testGetRecruiters() {

        int userId = anyInt();

        recruiterRepository.getRecruiters(userId);
        verify(recruiterService).getRecruiters(userId);
    }


}
