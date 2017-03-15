package com.vishesh.tpc_stud.core;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.dagger.AppModule;
import com.vishesh.tpc_stud.core.dagger.RetrofitModule;
import com.vishesh.tpc_stud.core.dagger.MockDataModule;

public class MockTpcStudApplication extends TpcStudApplication {

    @Override
    protected void setupDagger() {
        tpcStudAppComponent = DaggerTestAppComponent.builder()
                .appModule(new AppModule(this))
                .mockDataModule(new MockDataModule())
                .retrofitModule(new RetrofitModule(this.getString(R.string.base_url)))
                .build();
    }
}
