package com.vishesh.tpc_stud.gpa.presenters;

import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.gpa.presenters.GpaPresenter;
import com.vishesh.tpc_stud.gpa.useCases.GetGpaUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GpaPresenterTest {

    private GpaPresenter gpaPresenter;

    @Mock
    private GpaPresenter.GpaView gpaView;
    @Mock
    private LocalCache localCache;
    @Mock
    private GetGpaUseCase getGpaUseCase;


    @Before
    public void setUp() throws Exception {
        gpaPresenter = new GpaPresenter(localCache, getGpaUseCase);
        gpaPresenter.setView(gpaView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onStart_getGpa() throws Exception {

        gpaPresenter.onStart();

        verify(gpaView).showLoader();
        verify(getGpaUseCase).execute(any(DisposableSingleObserver.class),
                anyInt(), any(Object.class));
    }

}