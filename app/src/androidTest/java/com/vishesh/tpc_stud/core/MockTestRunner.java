package com.vishesh.tpc_stud.core;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class MockTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MockTpcStudApplication.class.getName(), context);
    }
}
