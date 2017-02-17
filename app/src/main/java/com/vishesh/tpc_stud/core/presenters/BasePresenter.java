package com.vishesh.tpc_stud.core.presenters;

/**
 * Created by vishesh on 17/2/17.
 */
public abstract class BasePresenter {

    public abstract void resume();

    public abstract void pause();

    public abstract void destroy();

    protected void handleError(Throwable throwable){

    }
}
