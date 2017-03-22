package com.vishesh.tpc_stud.dashboard.models;

/**
 * Created by vishesh on 25/2/17.
 */

public enum Network {

    GITHUB("Github"), LINKEDIN("LinkedIn"), WEBSITE("Website"), OTHER("Other");

    private final String networkName;

    Network(String networkName) {
        this.networkName = networkName;
    }

    public String getNetworkName() {
        return networkName;
    }
}
