package com.vishesh.placement.core.presenters;

public abstract class BasePresenter {

    public abstract void resume();

    public abstract void pause();

    public abstract void destroy();

    protected void handleError(Throwable throwable){

    }
}
