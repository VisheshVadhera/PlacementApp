package com.vishesh.tpc_stud.dashboard.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.ActivityComponent;
import com.vishesh.tpc_stud.core.helpers.Bus;
import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.core.views.BaseFragment;
import com.vishesh.tpc_stud.dashboard.adapters.ProfileItemAdapter;
import com.vishesh.tpc_stud.dashboard.busEvents.CvTapEvent;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;
import com.vishesh.tpc_stud.dashboard.presenters.ProfilePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;


public class ProfileFragment
        extends BaseFragment
        implements ProfilePresenter.ProfileView {

    @BindView(R.id.recycler_view_profile)
    RecyclerView recyclerViewProfile;

    @Inject
    ProfileItemAdapter profileItemAdapter;
    @Inject
    ProfilePresenter profilePresenter;
    @Inject
    Bus bus;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    protected void injectDependencies() {
        getDependencyInjector(ActivityComponent.class)
                .inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profilePresenter.setProfileView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        profilePresenter.onStart();
        bus.asFlowable()
                .subscribe(new BusEventConsumer());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void showProfile(User user, UserProfile userProfile) {
        profileItemAdapter.setData(user, userProfile);
        recyclerViewProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProfile.setAdapter(profileItemAdapter);
    }

    @Override
    public void openFileExplorer() {

    }

    @Override
    public void openPdfViewer() {

    }

    private class BusEventConsumer implements Consumer<Object> {

        @Override
        public void accept(Object event) throws Exception {
            if (event instanceof CvTapEvent) {
                profilePresenter.onCvTapped();
            }
        }
    }
}
