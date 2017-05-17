package com.vishesh.placement.core.dagger;

import android.content.Context;
import android.preference.PreferenceManager;

import com.vishesh.placement.core.cache.LocalCache;
import com.vishesh.placement.core.cache.PreferencesCache;

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
