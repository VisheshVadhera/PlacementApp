package com.vishesh.placement.networkProfiles.busEvents;

import com.vishesh.placement.networkProfiles.models.NetworkProfile;

public class NetworkProfileItemClickedEvent {

    private final NetworkProfile networkProfile;

    public NetworkProfileItemClickedEvent(NetworkProfile networkProfile) {
        this.networkProfile = networkProfile;
    }

    public NetworkProfile getNetworkProfile() {
        return networkProfile;
    }
}
