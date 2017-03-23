package com.vishesh.tpc_stud.core.dagger;

import com.vishesh.tpc_stud.auth.views.LoginFragment;
import com.vishesh.tpc_stud.dashboard.views.DashboardFragment;
import com.vishesh.tpc_stud.dashboard.views.ProfileFragment;
import com.vishesh.tpc_stud.dashboard.views.RecruitersFragment;
import com.vishesh.tpc_stud.gpa.views.SemesterGradesFragment;
import com.vishesh.tpc_stud.networkProfiles.views.NetworkProfilesFragment;
import com.vishesh.tpc_stud.splash.views.SplashFragment;

public interface TpcStudAppComponent {

    void inject(LoginFragment loginFragment);

    void inject(DashboardFragment dashboardFragment);

    void inject(RecruitersFragment recruitersFragment);

    void inject(ProfileFragment profileFragment);

    void inject(SplashFragment splashFragment);

    void inject(NetworkProfilesFragment networkProfilesFragment);

    void inject(SemesterGradesFragment semesterGradesFragment);
}
