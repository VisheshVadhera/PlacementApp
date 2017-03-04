package com.vishesh.tpc_stud.splash;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;

public class SplashPresenter extends BasePresenter{

    private SplashView splashView;

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        splashView = null;
    }

    public void setView(SplashView view) {
        this.splashView = view;
    }

    public interface SplashView {
    }
}
