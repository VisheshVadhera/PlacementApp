package com.vishesh.placement.core.cache;

import java.util.Map;

import static com.vishesh.placement.core.constants.CacheConstants.DEFAULT_ACCESS_TOKEN;
import static com.vishesh.placement.core.constants.CacheConstants.DEFAULT_USER_ID;
import static com.vishesh.placement.core.constants.CacheConstants.PREF_ACCESS_TOKEN;
import static com.vishesh.placement.core.constants.CacheConstants.PREF_USER_ID;

public class FakePreferencesCache implements LocalCache {

    private final Map<String, Object> map;

    public FakePreferencesCache(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public void saveAccessToken(String accessToken) {
        map.put(PREF_ACCESS_TOKEN, accessToken);
    }

    @Override
    public String getAccessToken() {
        if (map.containsKey(PREF_ACCESS_TOKEN)) {
            return (String) map.get(PREF_ACCESS_TOKEN);
        }
        return DEFAULT_ACCESS_TOKEN;
    }

    @Override
    public int getUserId() {
        if (map.containsKey(PREF_USER_ID)) {
            return (int) map.get(PREF_USER_ID);
        }
        return DEFAULT_USER_ID;
    }

    @Override
    public void saveUserId(int userId) {
        map.put(PREF_USER_ID, userId);
    }

    @Override
    public void deleteAccessToken() {
        if (map.containsKey(PREF_ACCESS_TOKEN)) {
            map.remove(PREF_ACCESS_TOKEN);
        }
    }

    @Override
    public void deleteUserId() {
        if (map.containsKey(PREF_USER_ID)) {
            map.remove(PREF_USER_ID);
        }
    }
}
