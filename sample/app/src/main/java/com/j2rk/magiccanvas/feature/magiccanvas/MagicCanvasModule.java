package com.j2rk.magiccanvas.feature.magiccanvas;


import androidx.databinding.DataBindingUtil;

import com.j2rk.magiccanvas.R;
import com.j2rk.magiccanvas.databinding.ActivityMagicCanvasBinding;
import com.j2rk.magiccanvas.di.ActivityScope;
import com.j2rk.magiccanvas.feature.main.RendererType;

import dagger.Module;
import dagger.Provides;

import static com.j2rk.magiccanvas.feature.magiccanvas.MagicCanvasActivity.EXTRA_RENDERER_TYPE;

@Module
public abstract class MagicCanvasModule {

    @Provides
    @ActivityScope
    static ActivityMagicCanvasBinding provideBinding(MagicCanvasActivity activity) {
        return DataBindingUtil.setContentView(activity, R.layout.activity_magic_canvas);
    }

    @Provides
    @ActivityScope
    static MagicCanvasViewModel provideViewModel(RendererType rendererType) {
        return new MagicCanvasViewModel(rendererType);
    }

    @Provides
    @ActivityScope
    static RendererType provideRendererType(MagicCanvasActivity activity) {
        if (!activity.getIntent().hasExtra(EXTRA_RENDERER_TYPE)) {
            throw new IllegalArgumentException("No RendererType");
        }
        return (RendererType) activity.getIntent().getSerializableExtra(EXTRA_RENDERER_TYPE);
    }
}