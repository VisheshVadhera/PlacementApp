package com.vishesh.placement.core.dagger;

import com.vishesh.placement.auth.views.LoginFragment;
import com.vishesh.placement.dashboard.views.DashboardFragment;
import com.vishesh.placement.dashboard.views.ProfileFragment;
import com.vishesh.placement.dashboard.views.RecruitersFragment;
import com.vishesh.placement.semesterGrades.views.SemesterGradesFragment;
import com.vishesh.placement.networkProfiles.views.NetworkProfilesFragment;
import com.vishesh.placement.splash.views.SplashFragment;

public interface TpcStudAppComponent {

    void inject(LoginFragment loginFragment);

    void inject(DashboardFragment dashboardFragment);

    void inject(RecruitersFragment recruitersFragment);

    void inject(ProfileFragment profileFragment);

    void inject(SplashFragment splashFragment);

    void inject(NetworkProfilesFragment networkProfilesFragment);

    void inject(SemesterGradesFragment semesterGradesFragment);
}
