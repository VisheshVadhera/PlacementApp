package com.vishesh.placement.auth.useCases;

import com.vishesh.placement.common.repos.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Scheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class LoginUseCaseTest {

    private LoginUseCase loginUseCase;

    @Mock
    private Scheduler jobScheduler;
    @Mock
    private Scheduler postJobScheduler;
    @Mock
    private UserRepository userRepository;


    @Before
    public void setup() {
        loginUseCase = new LoginUseCase(jobScheduler,
                postJobScheduler, userRepository);
    }

    @Test
    public void testLoginUseCaseSingle() {
        Map<String, String> map = new HashMap<>();
        Object o = new Object();
        loginUseCase.buildSingle(map, o);

        verify(userRepository).emailLogin(map);
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }


}
