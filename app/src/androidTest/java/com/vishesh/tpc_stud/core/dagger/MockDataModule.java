package com.vishesh.tpc_stud.core.dagger;

import com.vishesh.tpc_stud.core.cache.FakePreferencesCache;
import com.vishesh.tpc_stud.core.cache.LocalCache;

import java.util.HashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockDataModule {

    @Provides
    @Singleton
    LocalCache provideLocalCache() {
        return new FakePreferencesCache(new HashMap<String, Object>());
    }
}
