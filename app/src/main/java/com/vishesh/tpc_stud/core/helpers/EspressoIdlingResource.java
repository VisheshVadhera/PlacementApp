package com.vishesh.tpc_stud.core.helpers;

import java.util.concurrent.atomic.AtomicInteger;

public class EspressoIdlingResource {

    private static final String RESOURCE = "GLOBAL";

    private static SimpleIdlingResource simpleIdlingResource =
            new SimpleIdlingResource(RESOURCE, new AtomicInteger(0));

    public static void increment() {
        simpleIdlingResource.increment();
    }

    public static void decrement() {
        simpleIdlingResource.decrement();
    }

    public static SimpleIdlingResource getIdlingResource() {
        return simpleIdlingResource;
    }
}
