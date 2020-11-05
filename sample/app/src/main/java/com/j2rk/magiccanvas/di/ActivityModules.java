package com.j2rk.magiccanvas.di;

import com.j2rk.magiccanvas.feature.magiccanvas.MagicCanvasActivity;
import com.j2rk.magiccanvas.feature.magiccanvas.MagicCanvasModule;
import com.j2rk.magiccanvas.feature.main.MainActivity;
import com.j2rk.magiccanvas.feature.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModules {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();


    @ActivityScope
    @ContributesAndroidInjector(modules = MagicCanvasModule.class)
    abstract MagicCanvasActivity magicCanvasActivity();
}