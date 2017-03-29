package com.vishesh.tpc_stud;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.TpcStudApplication;
import com.vishesh.tpc_stud.core.dagger.AppModule;
import com.vishesh.tpc_stud.core.dagger.DaggerTestAppComponent;
import com.vishesh.tpc_stud.core.dagger.MockDataModule;
import com.vishesh.tpc_stud.core.dagger.RetrofitModule;

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