package com.vishesh.tpc_stud.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.ActivityComponent;
import com.vishesh.tpc_stud.core.views.BaseFragment;

import javax.inject.Inject;

public class SplashFragment
        extends BaseFragment
        implements SplashPresenter.SplashView {

    @Inject
    private SplashPresenter splashPresenter;

    public SplashFragment() {
        setRetainInstance(true);
    }

    @Override
    protected void injectDependencies() {
        getDependencyInjector(ActivityComponent.class)
                .inject(this);
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
}
