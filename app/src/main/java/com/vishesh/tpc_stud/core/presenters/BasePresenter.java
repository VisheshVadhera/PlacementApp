package com.vishesh.tpc_stud.core.presenters;

public abstract class BasePresenter {

    public abstract void resume();

    public abstract void pause();

    public abstract void destroy();

    public abstract void unsetView();

    protected void handleError(Throwable throwable){

    }
}
