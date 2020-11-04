package com.j2rk.magiccanvas.feature.main;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.j2rk.magiccanvas.databinding.ActivityMainBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    MainViewModel viewModel;

    @Inject
    ActivityMainBinding binding;

    @Inject
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleView.setAdapter(adapter);
    }
}
