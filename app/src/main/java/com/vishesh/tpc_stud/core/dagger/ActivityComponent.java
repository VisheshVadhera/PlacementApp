package com.vishesh.tpc_stud.core.dagger;


import com.vishesh.tpc_stud.auth.views.LoginFragment;
import com.vishesh.tpc_stud.dashboard.views.DashboardFragment;
import com.vishesh.tpc_stud.dashboard.views.ProfileFragment;
import com.vishesh.tpc_stud.dashboard.views.RecruitersFragment;
import com.vishesh.tpc_stud.splash.views.SplashFragment;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {AppComponent.class},
        modules = {ActivityModule.class, SchedulersModule.class,
                MockApiServiceModule.class})
public interface ActivityComponent {

    void inject(LoginFragment loginFragment);

    void inject(DashboardFragment dashboardFragment);

    void inject(RecruitersFragment recruitersFragment);

    void inject(ProfileFragment profileFragment);

    void inject(SplashFragment splashFragment);
}
