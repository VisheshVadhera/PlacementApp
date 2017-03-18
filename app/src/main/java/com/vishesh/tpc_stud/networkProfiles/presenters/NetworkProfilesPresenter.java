package com.vishesh.tpc_stud.networkProfiles.presenters;

import com.vishesh.tpc_stud.core.presenters.BasePresenter;
import com.vishesh.tpc_stud.core.views.BaseView;
import com.vishesh.tpc_stud.networkProfiles.useCases.GetNetworkProfilesUseCase;

import javax.inject.Inject;


public class NetworkProfilesPresenter extends BasePresenter {

    private final GetNetworkProfilesUseCase getNetworkProfilesUseCase;

    private NetworkProfilesView networkProfilesView;

    @Inject
    public NetworkProfilesPresenter(GetNetworkProfilesUseCase getNetworkProfilesUseCase) {
        this.getNetworkProfilesUseCase = getNetworkProfilesUseCase;
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

    }

    public interface NetworkProfilesView extends BaseView {

    }
}
