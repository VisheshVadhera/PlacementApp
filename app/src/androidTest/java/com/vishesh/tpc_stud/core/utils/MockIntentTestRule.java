package com.vishesh.tpc_stud.core.utils;

import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.vishesh.tpc_stud.core.views.BaseActivity;


public class MockIntentTestRule<T extends BaseActivity> extends IntentsTestRule<T> {

    public MockIntentTestRule(Class<T> activityClass) {
        super(activityClass);
    }

    public MockIntentTestRule(Class<T> activityClass, boolean initialTouchMode) {
        super(activityClass, initialTouchMode);
    }

    public MockIntentTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity) {
        super(activityClass, initialTouchMode, launchActivity);
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
        getActivity().
    }
}
