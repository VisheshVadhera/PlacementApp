package com.vishesh.placement.core.idlingResources;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;


public class SimpleIdlingResource implements IdlingResource {

    private static final String COUNTER_CORRUPTED_MESSAGE = "Counter has been corrupted";
    private final String resourceName;
    private final AtomicInteger counter;

    private volatile ResourceCallback resourceCallback;

    public SimpleIdlingResource(String resourceName, AtomicInteger counter) {
        this.resourceName = resourceName;
        this.counter = counter;
    }

    @Override
    public String getName() {
        return resourceName;
    }

    @Override
    public boolean isIdleNow() {
        return counter.get() == 0;
    }

    public void increment() {
        counter.getAndIncrement();
    }

    public void decrement() {
        int val = counter.decrementAndGet();
        if (val == 0 && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }

        if (val < 0) {
            throw new IllegalArgumentException(COUNTER_CORRUPTED_MESSAGE);
        }
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }
}
