package com.j2rk.magiccanvas.feature.main;


import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.j2rk.magiccanvas.R;
import com.j2rk.magiccanvas.databinding.ActivityMainBinding;
import com.j2rk.magiccanvas.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainModule {

    @Provides
    @ActivityScope
    static ActivityMainBinding provideBinding(MainActivity activity) {
        return DataBindingUtil.setContentView(activity, R.layout.activity_main);
    }

    @Provides
    @ActivityScope
    static MainViewModel provideViewModel(MainActivity activity) {
        return ViewModelProviders.of(activity).get(MainViewModel.class);
    }

    @Provides
    @ActivityScope
    static MainAdapter provideAdapter(MainViewModel viewModel) {
        return new MainAdapter(viewModel.getMenus());
    }
}