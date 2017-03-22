package com.vishesh.tpc_stud.networkProfiles.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.core.utils.StringFormatUtils;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.dashboard.models.Network;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;
import com.vishesh.tpc_stud.networkProfiles.constants.NetworkProfileConstants;
import com.vishesh.tpc_stud.networkProfiles.useCases.GetNetworkProfilesUseCase;
import com.vishesh.tpc_stud.networkProfiles.useCases.SaveNetworkProfileUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;


public class NetworkProfilesPresenter extends BasePresenter {

    private final GetNetworkProfilesUseCase getNetworkProfilesUseCase;
    private final SaveNetworkProfileUseCase saveNetworkProfileUseCase;
    private final LocalCache localCache;

    private NetworkProfilesView networkProfilesView;

    private List<NetworkProfile> networkProfiles;

    @Inject
    public NetworkProfilesPresenter(GetNetworkProfilesUseCase getNetworkProfilesUseCase,
                                    SaveNetworkProfileUseCase saveNetworkProfileUseCase,
                                    LocalCache localCache) {
        this.getNetworkProfilesUseCase = getNetworkProfilesUseCase;
        this.saveNetworkProfileUseCase = saveNetworkProfileUseCase;
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

    public void setNetworkProfilesView(NetworkProfilesView networkProfilesView) {
        this.networkProfilesView = networkProfilesView;
    }

    public void onStart() {
        networkProfilesView.showLoader();
        getNetworkProfilesUseCase.execute(new NetworkProfilesObserver(),
                localCache.getUserId(), new Object());
    }

    public void onNetworkProfileItemClicked(NetworkProfile networkProfile) {
        networkProfilesView.openExternalLink(networkProfile.getUrl());
    }

    public void onAddNetworkProfileClicked(Network network) {
        networkProfilesView.askForProfileUrl(network);
    }

    public void onNewNetworkProfileSaveClicked(String userInput, Network network) {

        if (StringFormatUtils.isUrlValid(userInput)) {

            NetworkProfile networkProfile = new NetworkProfile();
            networkProfile.setUrl(userInput);
            networkProfile.setNetwork(network);

            networkProfilesView.showLoader();
            saveNetworkProfileUseCase.execute(new NetworkProfileObserver(),
                    localCache.getUserId(),
                    networkProfile);
        } else {
            networkProfilesView.showMessage(NetworkProfileConstants.INVALID_URL_MESSAGE);
        }
    }

    private boolean isNetworkProfileAbsent(Network network) {
        for (NetworkProfile networkProfile : networkProfiles) {
            if (networkProfile.getNetwork().equals(network)) {
                return false;
            }
        }
        return true;
    }

    public interface NetworkProfilesView extends BaseView {

        void showNetworkProfiles(List<NetworkProfile> networkProfiles);

        void openExternalLink(String url);

        void allowGitHubProfileAddition();

        void allowLinkedInProfileAddition();

        void allowOtherProfilesAddition();

        void askForProfileUrl(Network network);

        void showUpdatedNetworkProfiles(List<NetworkProfile> networkProfiles);
    }

    private final class NetworkProfilesObserver extends DisposableSingleObserver<List<NetworkProfile>> {

        @Override
        public void onSuccess(List<NetworkProfile> networkProfiles) {

            NetworkProfilesPresenter.this.networkProfiles = networkProfiles;

            if (isNetworkProfileAbsent(Network.GITHUB)) {
                networkProfilesView.allowGitHubProfileAddition();
            } else if (isNetworkProfileAbsent(Network.LINKEDIN)) {
                networkProfilesView.allowLinkedInProfileAddition();
            } else {
                networkProfilesView.allowOtherProfilesAddition();
            }

            networkProfilesView.hideLoader();
            networkProfilesView.showNetworkProfiles(networkProfiles);

        }

        @Override
        public void onError(Throwable e) {
            networkProfilesView.hideLoader();
            handleError(e);
        }
    }

    private final class NetworkProfileObserver extends DisposableSingleObserver<NetworkProfile> {

        @Override
        public void onSuccess(NetworkProfile networkProfile) {
            networkProfilesView.hideLoader();
            networkProfiles.add(networkProfile);
            networkProfilesView.showUpdatedNetworkProfiles(networkProfiles);
        }

        @Override
        public void onError(Throwable e) {
            networkProfilesView.hideLoader();
            handleError(e);
        }
    }
}
