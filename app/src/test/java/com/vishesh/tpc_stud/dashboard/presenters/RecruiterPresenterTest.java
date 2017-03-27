package com.vishesh.tpc_stud.dashboard.presenters;

import com.vishesh.tpc_stud.core.cache.LocalCache;
import com.vishesh.tpc_stud.dashboard.mappers.RecruiterModelMapper;
import com.vishesh.tpc_stud.dashboard.useCases.GetRecruitersUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RecruiterPresenterTest {

    @Mock
    private GetRecruitersUseCase getRecruitersUseCase;
    @Mock
    private LocalCache localCache;
    @Mock
    private RecruiterModelMapper recruiterModelMapper;
    @Mock
    private RecruitersPresenter.RecruitersView recruitersView;

    private RecruitersPresenter recruitersPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        recruitersPresenter = new RecruitersPresenter(getRecruitersUseCase,
                localCache, recruiterModelMapper);
        recruitersPresenter.setView(recruitersView);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onStart_getJobOffers() {
        recruitersPresenter.initialize();

        verify(recruitersView).showLoader();
        verify(getRecruitersUseCase).execute(any(DisposableSingleObserver.class),
                any(Integer.class), any(Void.class));
    }

}
