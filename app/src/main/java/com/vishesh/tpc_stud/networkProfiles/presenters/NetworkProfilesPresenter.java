package com.vishesh.tpc_stud.networkProfiles.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.dashboard.models.Network;
import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;
import com.vishesh.tpc_stud.networkProfiles.useCases.GetNetworkProfilesUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;


public class NetworkProfilesPresenter extends BasePresenter {

    private final GetNetworkProfilesUseCase getNetworkProfilesUseCase;
    private final LocalCache localCache;

    private NetworkProfilesView networkProfilesView;
    private List<NetworkProfile> networkProfiles;

    @Inject
    public NetworkProfilesPresenter(GetNetworkProfilesUseCase getNetworkProfilesUseCase,
                                    LocalCache localCache) {
        this.getNetworkProfilesUseCase = getNetworkProfilesUseCase;
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

    public void addNetworkProfileClicked(Network network) {
        networkProfilesView.askForProfileUrl();
    }

    private boolean isNetworkProfilePresent(Network network) {
        for (NetworkProfile networkProfile : networkProfiles) {
            if (networkProfile.getNetwork().equals(network)) {
                return true;
            }
        }
        return false;
    }

    public interface NetworkProfilesView extends BaseView {

        void showNetworkProfiles(List<NetworkProfile> networkProfiles);

        void openExternalLink(String url);

        void allowGitHubProfileAddition();

        void allowLinkedProfileAddition();

        void allowOtherProfilesAddition();

        void askForProfileUrl();
    }

    private final class NetworkProfilesObserver extends DisposableSingleObserver<List<NetworkProfile>> {

        @Override
        public void onSuccess(List<NetworkProfile> networkProfiles) {

            NetworkProfilesPresenter.this.networkProfiles = networkProfiles;

            networkProfilesView.hideLoader();
            networkProfilesView.showNetworkProfiles(networkProfiles);

            if (!isNetworkProfilePresent(Network.GITHUB)) {
                networkProfilesView.allowGitHubProfileAddition();
            } else if (!isNetworkProfilePresent(Network.LINKEDIN)) {
                networkProfilesView.allowLinkedProfileAddition();
            } else {
                networkProfilesView.allowOtherProfilesAddition();
            }
        }

        @Override
        public void onError(Throwable e) {
            networkProfilesView.hideLoader();
            handleError(e);
        }
    }
}
