package com.vishesh.tpc_stud.semesterGrades.presenters;

import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.semesterGrades.useCases.GetSemesterGradesUseCase;

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
public class SemesterGradesPresenterTest {

    private SemesterGradesPresenter semesterGradesPresenter;

    @Mock
    private SemesterGradesPresenter.SemesterGradesView semesterGradesView;
    @Mock
    private LocalCache localCache;
    @Mock
    private GetSemesterGradesUseCase getSemesterGradesUseCase;


    @Before
    public void setUp() throws Exception {
        semesterGradesPresenter = new SemesterGradesPresenter(localCache, getSemesterGradesUseCase);
        semesterGradesPresenter.setView(semesterGradesView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onStart_getGpa() throws Exception {

        semesterGradesPresenter.initialize();

        verify(semesterGradesView).showLoader();
        verify(getSemesterGradesUseCase).execute(any(DisposableSingleObserver.class),
                anyInt(), any(Object.class));
    }

}