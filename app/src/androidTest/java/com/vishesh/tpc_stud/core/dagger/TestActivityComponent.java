package com.vishesh.tpc_stud.core.dagger;

import dagger.Component;

@PerActivity
@Component(
        dependencies = {TestAppComponent.class},
        modules = {ActivityModule.class, SchedulersModule.class,
                MockApiServiceModule.class})
public interface TestActivityComponent extends TpcStudActivityComponent {
}
