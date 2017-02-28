package com.vishesh.tpc_stud.dashboard.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.ActivityComponent;
import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.core.views.BaseFragment;
import com.vishesh.tpc_stud.dashboard.adapters.ProfileItemAdapter;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;
import com.vishesh.tpc_stud.dashboard.presenters.ProfilePresenter;

import javax.inject.Inject;

import butterknife.BindView;


public class ProfileFragment
        extends BaseFragment
        implements ProfilePresenter.ProfileView {

    @BindView(R.id.recycler_view_profile)
    RecyclerView recyclerViewProfile;

    @Inject
    ProfileItemAdapter profileItemAdapter;
    @Inject
    ProfilePresenter profilePresenter;

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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void showProfile(User user, UserProfile userProfile) {
        profileItemAdapter.setData(user, userProfile);
        recyclerViewProfile.setAdapter(profileItemAdapter);
    }
}
