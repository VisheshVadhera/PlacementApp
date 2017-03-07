package com.vishesh.tpc_stud.auth;

import com.vishesh.tpc_stud.auth.useCases.UpdateUserUseCase;
import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.core.repos.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Scheduler;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserUseCaseTest {

    private UpdateUserUseCase updateUserUseCase;

    @Mock
    private Scheduler jobScheduler;
    @Mock
    private Scheduler postJobScheduler;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        updateUserUseCase = new UpdateUserUseCase(jobScheduler,
                postJobScheduler, userRepository);
    }

    @Test
    public void testUpdateUserUseCaseObservable() {
        Integer userId = anyInt();
        User user = any(User.class);

        updateUserUseCase.buildObservable(userId, user);

        verify(userRepository).updateUser(userId, user);
        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(jobScheduler);
        verifyZeroInteractions(postJobScheduler);
    }
}
