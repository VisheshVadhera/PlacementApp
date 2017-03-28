package com.vishesh.tpc_stud.splash.presenters;

import android.text.TextUtils;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.cache.LocalCache;

import javax.inject.Inject;

public class SplashPresenter extends BasePresenter {

    private SplashView splashView;

    private final LocalCache localCache;

    @Inject
    public SplashPresenter(LocalCache localCache) {
        this.localCache = localCache;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void unsetView() {
        splashView = null;
    }

    public void setView(SplashView view) {
        this.splashView = view;
    }

    public void onStart() {
        String accessToken = localCache.getAccessToken();

        if (TextUtils.isEmpty(accessToken)) {
            splashView.openLoginScreen();
        } else {
            splashView.openDashboard();
        }
    }

    public interface SplashView {

        void openLoginScreen();

        void openDashboard();
    }
}
