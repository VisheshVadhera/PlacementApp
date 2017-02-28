package com.vishesh.tpc_stud.core.repos;

import android.content.SharedPreferences;

/**
 * Created by vishesh on 18/2/17.
 */
public class PreferencesCache implements LocalCache {

    private static final String PREF_ACCESS_TOKEN = "PREF_ACCESS_TOKEN";
    private static final String DEFAULT_ACCESS_TOKEN = "";

    private static final String PREF_USER_ID = "PREF_USER_ID";
    private static final int DEFAULT_USER_ID = 0;


    private final SharedPreferences sharedPreferences;

    public PreferencesCache(SharedPreferences rxSharedPreferences) {
        this.sharedPreferences = rxSharedPreferences;
    }

    @Override
    public void saveAccessToken(String accessToken) {
        sharedPreferences
                .edit()
                .putString(PREF_ACCESS_TOKEN, accessToken)
                .apply();
    }

    @Override
    public String getAccessToken() {
        return sharedPreferences.getString(PREF_ACCESS_TOKEN, DEFAULT_ACCESS_TOKEN);
    }

    @Override
    public int getUserId() {
        return sharedPreferences.getInt(PREF_USER_ID, DEFAULT_USER_ID);
    }

    @Override
    public void saveUserId(int userId) {
        sharedPreferences
                .edit()
                .putInt(PREF_USER_ID, userId)
                .apply();
    }
}
