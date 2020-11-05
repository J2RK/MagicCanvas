package com.j2rk.magiccanvas.feature.magiccanvas;

import android.os.Bundle;

import com.j2rk.magiccanvas.databinding.ActivityMagicCanvasBinding;
import com.j2rk.magiccanvas.feature.main.RendererType;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MagicCanvasActivity extends DaggerAppCompatActivity {

    public static final String EXTRA_RENDERER_TYPE = "renderer type";

    @Inject
    ActivityMagicCanvasBinding binding;

    @Inject
    MagicCanvasViewModel viewModel;

    @Inject
    RendererType rendererType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(rendererType.getName());
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        getLifecycle().addObserver(viewModel);
    }
}
