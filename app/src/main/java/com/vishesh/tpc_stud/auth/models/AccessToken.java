package com.vishesh.tpc_stud.auth.models;

import com.google.gson.annotations.Expose;

public class AccessToken {

    @Expose
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
