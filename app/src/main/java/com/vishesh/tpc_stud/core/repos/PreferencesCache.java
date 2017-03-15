package com.vishesh.tpc_stud.core.repos;

import android.content.SharedPreferences;

import com.vishesh.tpc_stud.core.constants.CacheConstants;

/**
 * Created by vishesh on 18/2/17.
 */
public class PreferencesCache implements LocalCache {

    private final SharedPreferences sharedPreferences;

    public PreferencesCache(SharedPreferences rxSharedPreferences) {
        this.sharedPreferences = rxSharedPreferences;
    }

    @Override
    public void saveAccessToken(String accessToken) {
        sharedPreferences
                .edit()
                .putString(CacheConstants.PREF_ACCESS_TOKEN, accessToken)
                .apply();
    }

    @Override
    public String getAccessToken() {
        return sharedPreferences.getString(CacheConstants.PREF_ACCESS_TOKEN, CacheConstants.DEFAULT_ACCESS_TOKEN);
    }

    @Override
    public int getUserId() {
        return sharedPreferences.getInt(CacheConstants.PREF_USER_ID, CacheConstants.DEFAULT_USER_ID);
    }

    @Override
    public void saveUserId(int userId) {
        sharedPreferences
                .edit()
                .putInt(CacheConstants.PREF_USER_ID, userId)
                .apply();
    }

    @Override
    public void deleteAccessToken() {
        sharedPreferences
                .edit()
                .remove(CacheConstants.PREF_ACCESS_TOKEN)
                .apply();
    }

    @Override
    public void deleteUserId() {
        sharedPreferences
                .edit()
                .remove(CacheConstants.PREF_USER_ID)
                .apply();
    }
}
