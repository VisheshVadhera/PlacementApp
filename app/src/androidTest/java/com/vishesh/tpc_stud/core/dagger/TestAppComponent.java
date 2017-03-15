package com.vishesh.tpc_stud.core.dagger;

import com.vishesh.tpc_stud.core.dagger.AppModule;
import com.vishesh.tpc_stud.core.dagger.MockRetrofitModule;
import com.vishesh.tpc_stud.core.dagger.RetrofitModule;
import com.vishesh.tpc_stud.core.dagger.TpcStudAppComponent;
import com.vishesh.tpc_stud.core.dagger.MockDataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class, MockRetrofitModule.class,
        RetrofitModule.class, MockDataModule.class
})
public interface TestAppComponent extends TpcStudAppComponent {

}
