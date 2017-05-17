package com.vishesh.placement.networkProfiles.presenters;

import com.vishesh.placement.core.presenters.BasePresenter;
import com.vishesh.placement.core.cache.LocalCache;
import com.vishesh.placement.core.utils.StringFormatUtils;
import com.vishesh.placement.core.views.BaseView;
import com.vishesh.placement.networkProfiles.models.Network;
import com.vishesh.placement.networkProfiles.models.NetworkProfile;
import com.vishesh.placement.networkProfiles.constants.NetworkProfileConstants;
import com.vishesh.placement.networkProfiles.useCases.GetNetworkProfilesUseCase;
import com.vishesh.placement.networkProfiles.useCases.SaveNetworkProfileUseCase;

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
        getNetworkProfilesUseCase.dispose();
        saveNetworkProfileUseCase.dispose();
        networkProfilesView = null;
    }

    public void setNetworkProfilesView(NetworkProfilesView networkProfilesView) {
        this.networkProfilesView = networkProfilesView;
    }

    public void initialize() {
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
