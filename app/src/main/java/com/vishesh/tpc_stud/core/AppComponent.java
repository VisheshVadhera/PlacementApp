package com.vishesh.tpc_stud.core;

import com.vishesh.tpc_stud.core.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vishesh on 12/2/17.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {


}
