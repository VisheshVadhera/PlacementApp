package com.vishesh.placement.splash.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vishesh.placement.R;
import com.vishesh.placement.auth.views.LoginFragment;
import com.vishesh.placement.core.views.BaseFragment;
import com.vishesh.placement.dashboard.views.DashboardFragment;
import com.vishesh.placement.splash.presenters.SplashPresenter;

import javax.inject.Inject;

public class SplashFragment
        extends BaseFragment
        implements SplashPresenter.SplashView {

    @Inject
    SplashPresenter splashPresenter;

    public SplashFragment() {
        setRetainInstance(true);
    }

    @Override
    protected void injectDependencies() {
        getDependencyInjector().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        splashPresenter.onStart();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        splashPresenter.setView(this);
    }

    @Override
    public void openLoginScreen() {
        Intent loginIntent = LoginFragment.createLoginIntent(getContext());
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void openDashboard() {
        Intent dashboardIntent = DashboardFragment.createIntent(getContext());
        startActivity(dashboardIntent);
        finish();
    }
}
