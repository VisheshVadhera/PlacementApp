package com.vishesh.tpc_stud.core.repos;

/**
 * Created by vishesh on 18/2/17.
 */
public interface LocalCache {
    void saveAccessToken(String accessToken);

    String getAccessToken();
}
