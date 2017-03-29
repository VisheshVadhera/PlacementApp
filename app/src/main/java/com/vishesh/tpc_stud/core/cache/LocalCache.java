package com.vishesh.tpc_stud.core.cache;

public interface LocalCache {

    void saveAccessToken(String accessToken);

    String getAccessToken();

    int getUserId();

    void saveUserId(int userId);

    void deleteAccessToken();

    void deleteUserId();
}