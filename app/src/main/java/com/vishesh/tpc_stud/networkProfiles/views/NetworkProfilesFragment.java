package com.vishesh.tpc_stud.networkProfiles.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.views.BaseFragment;
import com.vishesh.tpc_stud.networkProfiles.presenters.NetworkProfilesPresenter;

import javax.inject.Inject;


public class NetworkProfilesFragment
        extends BaseFragment
        implements NetworkProfilesPresenter.NetworkProfilesView {

    @Inject
    NetworkProfilesPresenter networkProfilesPresenter;

    public static NetworkProfilesFragment newInstance() {
        return new NetworkProfilesFragment();
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, NetworkProfilesActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        networkProfilesPresenter.setNetworkProfilesView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        networkProfilesPresenter.onStart();
    }

    @Override
    protected void injectDependencies() {
        getDependencyInjector().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_network_profiles;
    }
}
