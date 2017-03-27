package com.vishesh.tpc_stud.core.dagger;

import android.content.Context;
import android.preference.PreferenceManager;

import com.vishesh.tpc_stud.core.cache.LocalCache;
import com.vishesh.tpc_stud.core.cache.PreferencesCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    private final Context context;

    public DataModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    LocalCache provideLocalCache() {
        return new PreferencesCache(PreferenceManager
                .getDefaultSharedPreferences(context));
    }
}
