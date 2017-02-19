package com.vishesh.tpc_stud.core.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vishesh.tpc_stud.core.repos.LocalCache;
import com.vishesh.tpc_stud.core.repos.PreferencesCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vishesh on 18/2/17.
 */
@Module
public class DataModule {

    private final Context context;

    public DataModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    SharedPreferences provideRxSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    LocalCache provideLocalCache(SharedPreferences sharedPreferences) {
        return new PreferencesCache(sharedPreferences);
    }
}
