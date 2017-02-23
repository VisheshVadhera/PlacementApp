package com.vishesh.tpc_stud.core;


import com.vishesh.tpc_stud.core.modules.ActivityModule;
import com.vishesh.tpc_stud.core.scopes.PerActivity;
import com.vishesh.tpc_stud.dashboard.DashboardActivity;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {AppComponent.class},
        modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(DashboardActivity dashboardActivity);

}
