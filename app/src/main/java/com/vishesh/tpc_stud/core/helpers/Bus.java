package com.vishesh.tpc_stud.core.helpers;

import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

public class Bus {

    private final Relay<Object> bus;

    public Bus() {
        bus = PublishRelay.create().toSerialized();
    }


    public void post(Object o) {
        bus.accept(o);
    }

    public Flowable<Object> asFlowable() {
        return bus.toFlowable(BackpressureStrategy.LATEST);
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
}
