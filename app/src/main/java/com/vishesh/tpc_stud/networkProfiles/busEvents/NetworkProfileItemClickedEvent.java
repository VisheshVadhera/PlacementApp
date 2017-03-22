package com.vishesh.tpc_stud.networkProfiles.busEvents;

import com.vishesh.tpc_stud.dashboard.models.NetworkProfile;

public class NetworkProfileItemClickedEvent {

    private final NetworkProfile networkProfile;

    public NetworkProfileItemClickedEvent(NetworkProfile networkProfile) {
        this.networkProfile = networkProfile;
    }

    public NetworkProfile getNetworkProfile() {
        return networkProfile;
    }
}
