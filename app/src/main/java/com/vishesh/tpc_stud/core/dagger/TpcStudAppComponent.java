package com.vishesh.tpc_stud.core.dagger;

import com.vishesh.tpc_stud.core.helpers.Bus;
import com.vishesh.tpc_stud.core.repos.LocalCache;

import retrofit2.Retrofit;
import retrofit2.mock.MockRetrofit;

public interface TpcStudAppComponent {

    Retrofit retrofit();

    LocalCache localCache();

    MockRetrofit mockRetrofit();

    Bus bus();
}
