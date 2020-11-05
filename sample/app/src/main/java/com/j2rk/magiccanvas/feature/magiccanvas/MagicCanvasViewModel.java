package com.j2rk.magiccanvas.feature.magiccanvas;


import androidx.databinding.BaseObservable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.j2rk.magiccanvas.feature.magiccanvas.base.MagicCanvasRenderer;
import com.j2rk.magiccanvas.feature.main.RendererType;

public class MagicCanvasViewModel extends BaseObservable implements LifecycleObserver {

    private MagicCanvasRenderer<?> renderer;

    public MagicCanvasViewModel(RendererType type) {
        renderer = type.getRenderer();
    }

    public MagicCanvasRenderer<?> getRenderer(){
        return renderer;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        renderer.onResume();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        renderer.onPause();
    }

}
