package com.vishesh.tpc_stud.core;


import com.vishesh.tpc_stud.core.modules.ActivityModule;
import com.vishesh.tpc_stud.dashboard.DashboardActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(DashboardActivity dashboardActivity);

}
