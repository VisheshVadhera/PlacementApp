package com.vishesh.placement;

import com.vishesh.placement.core.dagger.AppModule;
import com.vishesh.placement.core.dagger.DaggerTestAppComponent;
import com.vishesh.placement.core.dagger.MockDataModule;
import com.vishesh.placement.core.dagger.RetrofitModule;

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
