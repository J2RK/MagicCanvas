package com.j2rk.magiccanvas.di;

import com.j2rk.magiccanvas.feature.main.MainActivity;
import com.j2rk.magiccanvas.feature.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModules {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
