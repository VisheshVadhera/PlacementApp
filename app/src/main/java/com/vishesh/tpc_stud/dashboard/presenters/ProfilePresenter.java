
package com.vishesh.tpc_stud.dashboard.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;
import com.vishesh.tpc_stud.dashboard.useCases.GetProfileUseCase;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by vishesh on 24/2/17.
 */

public class ProfilePresenter
        extends BasePresenter {


    private ProfileView profileView;

    private final GetProfileUseCase getProfileUseCase;
    private final LocalCache localCache;

    @Inject
    public ProfilePresenter(GetProfileUseCase getProfileUseCase,
                            LocalCache localCache) {
        this.getProfileUseCase = getProfileUseCase;
        this.localCache = localCache;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void setProfileView(ProfileView profileView) {
        this.profileView = profileView;
    }

    public void onStart() {
        profileView.showLoader();
        getProfileUseCase.execute(new ProfileObserver(), localCache.getUserId(), null);
    }

    public interface ProfileView extends BaseView {

        void showProfile(UserProfile userProfile);
    }

    private final class ProfileObserver extends DisposableSingleObserver<UserProfile> {

        @Override
        public void onSuccess(UserProfile userProfile) {
            profileView.showProfile(userProfile);
        }

        @Override
        public void onError(Throwable e) {
            profileView.hideLoader();
            handleError(e);
        }
    }
}
