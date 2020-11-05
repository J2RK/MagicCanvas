package com.j2rk.magiccanvas.feature.magiccanvas.base;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MagicCanvasRenderer<T extends MagicTool> implements GLSurfaceView.Renderer {

    private T item;

    public MagicCanvasRenderer(T item) {
        this.item = item;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {

    }

    public void onPause() {
        item.onPause();
    }

    public void onResume() {
        item.onResume();
    }

    public void onDestroy() {
        item.onDestroy();
    }
}