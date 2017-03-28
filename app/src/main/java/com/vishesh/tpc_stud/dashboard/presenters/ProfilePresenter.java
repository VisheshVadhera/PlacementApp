
package com.vishesh.tpc_stud.dashboard.presenters;

import android.text.TextUtils;

import com.fernandocejas.arrow.optional.Optional;
import com.vishesh.tpc_stud.dashboard.useCases.GetCurrentUserUseCase;
import com.vishesh.tpc_stud.common.models.User;
import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.cache.LocalCache;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.dashboard.models.UserProfile;
import com.vishesh.tpc_stud.dashboard.useCases.GetProfileUseCase;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

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

    public void setProfileView(ProfileView profileView) {
        this.profileView = profileView;
    }

    public void initialize() {
        profileView.showLoader();
        getCurrentUserUseCase.execute(new CurrentUserObserver(), new Object(), new Object());
    }

    @Override
    public void destroy() {
        getCurrentUserUseCase.dispose();
        getProfileUseCase.dispose();
    }

    @Override
    public void unsetView() {
        profileView = null;
    }

    public void onCvTapped() {
        if (userProfileOptional.isPresent()) {

            UserProfile userProfile = userProfileOptional.get();

            if (TextUtils.isEmpty(userProfile.getCvUrl())) {
//           TODO:     profileView.openFileExplorer(CV_FILE_TYPE);
            } else {
//            TODO:    profileView.openPdfViewer(userProfile.getCvUrl());
            }
        }
    }

    public void onNetworkProfileTapped() {
        profileView.openNetworkProfilesScreen();
    }

    public void onFileReceived(File file) {

    }

    public void onGpaTapped() {
        profileView.openSemesterGradesScreen();
    }

    public interface ProfileView extends BaseView {

        void showProfile(User user, UserProfile userProfile);

        void openFileExplorer(String fileType);

        void openPdfViewer(String pdfUrl);

        void openNetworkProfilesScreen();

        void openSemesterGradesScreen();
    }

    private final class ProfileObserver extends DisposableSingleObserver<UserProfile> {

        @Override
        public void onSuccess(UserProfile userProfile) {
            if (profileView != null) {
                profileView.hideLoader();
                if (userOptional.isPresent()) {
                    userProfileOptional = Optional.of(userProfile);
                    profileView.showProfile(userOptional.get(), userProfile);
                }
            }
        }

        @Override
        public void onError(Throwable e) {
            if (profileView != null) {
                profileView.hideLoader();
                handleError(e);
            }
        }
    }

    private final class CurrentUserObserver extends DisposableSingleObserver<User> {

        @Override
        public void onSuccess(User user) {
            if (profileView != null) {
                localCache.saveUserId(user.getId());
                userOptional = Optional.of(user);
                getProfileUseCase.execute(new ProfileObserver(), localCache.getUserId(), null);
            }
        }

        @Override
        public void onError(Throwable e) {
            if (profileView != null) {
                profileView.hideLoader();
                handleError(e);
            }
        }
    }
}
