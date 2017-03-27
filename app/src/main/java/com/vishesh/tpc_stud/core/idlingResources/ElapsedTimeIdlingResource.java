package com.vishesh.tpc_stud.core.idlingResources;

import android.support.test.espresso.IdlingResource;


public class ElapsedTimeIdlingResource implements IdlingResource {

    private final long waitingTimeMills;
    private final long startTimeMills;

    private ResourceCallback resourceCallback;

    public ElapsedTimeIdlingResource(long waitingTimeMills) {
        this.waitingTimeMills = waitingTimeMills;
        startTimeMills = System.currentTimeMillis();
    }

    @Override
    public String getName() {
        return ElapsedTimeIdlingResource.class.getName() + waitingTimeMills;
    }

    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - startTimeMills;
        boolean isIdle = (elapsed >= waitingTimeMills);

        if (isIdle) {
            resourceCallback.onTransitionToIdle();
        }

        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;
    }
}
