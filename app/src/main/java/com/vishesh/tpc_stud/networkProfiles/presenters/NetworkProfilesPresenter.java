package com.vishesh.tpc_stud.networkProfiles.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.core.views.BaseView;
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

    public interface NetworkProfilesView extends BaseView {

        void showNetworkProfiles(List<NetworkProfile> networkProfiles);
    }

    private final class NetworkProfilesObserver extends DisposableSingleObserver<List<NetworkProfile>> {

        @Override
        public void onSuccess(List<NetworkProfile> networkProfiles) {
            networkProfilesView.hideLoader();
            NetworkProfilesPresenter.this.networkProfiles = networkProfiles;
            networkProfilesView.showNetworkProfiles(networkProfiles);
        }

        @Override
        public void onError(Throwable e) {
            networkProfilesView.hideLoader();
            handleError(e);
        }
    }
}
