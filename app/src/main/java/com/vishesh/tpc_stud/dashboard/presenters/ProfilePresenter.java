
package com.vishesh.tpc_stud.dashboard.presenters;

import android.text.TextUtils;

import com.fernandocejas.arrow.optional.Optional;
import com.vishesh.tpc_stud.auth.useCases.GetCurrentUserUseCase;
import com.vishesh.tpc_stud.core.models.User;
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

    private static final String CV_FILE_TYPE = "application/pdf";

    private Optional<User> userOptional = Optional.absent();
    private Optional<UserProfile> userProfileOptional = Optional.absent();

    private ProfileView profileView;

    private final GetProfileUseCase getProfileUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final LocalCache localCache;

    @Inject
    public ProfilePresenter(GetProfileUseCase getProfileUseCase,
                            GetCurrentUserUseCase getCurrentUserUseCase,
                            LocalCache localCache) {
        this.getProfileUseCase = getProfileUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
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
        getCurrentUserUseCase.execute(new CurrentUserObserver(), null, null);
    }

    public void onCvTapped() {
        if (userProfileOptional.isPresent()) {

            UserProfile userProfile = userProfileOptional.get();

            if (TextUtils.isEmpty(userProfile.getCvUrl())) {
                profileView.openFileExplorer(CV_FILE_TYPE);
            } else {
                profileView.openPdfViewer();
            }
        }
    }

    public interface ProfileView extends BaseView {

        void showProfile(User user, UserProfile userProfile);

        void openFileExplorer(String fileType);

        void openPdfViewer();
    }

    private final class ProfileObserver extends DisposableSingleObserver<UserProfile> {

        @Override
        public void onSuccess(UserProfile userProfile) {
            profileView.hideLoader();
            if (userOptional.isPresent()) {
                userProfileOptional = Optional.of(userProfile);
                profileView.showProfile(userOptional.get(), userProfile);
            }
        }

        @Override
        public void onError(Throwable e) {
            profileView.hideLoader();
            handleError(e);
        }
    }

    private final class CurrentUserObserver extends DisposableSingleObserver<User> {

        @Override
        public void onSuccess(User user) {
            userOptional = Optional.of(user);
            getProfileUseCase.execute(new ProfileObserver(), localCache.getUserId(), null);
        }

        @Override
        public void onError(Throwable e) {
            profileView.hideLoader();
            handleError(e);
        }
    }
}
