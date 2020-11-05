package com.j2rk.magiccanvas.feature.magiccanvas;

import android.annotation.SuppressLint;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;

import androidx.databinding.BindingAdapter;


public class MagicCanvasBinding {
    @BindingAdapter("renderer")
    public static void setRenderer(GLSurfaceView view, GLSurfaceView.Renderer renderer) {
        view.setEGLContextClientVersion(2);
        view.setRenderer(renderer);
    }

    @SuppressLint("ClickableViewAccessibility")
    @BindingAdapter("touchListener")
    public static void setTouchListener(GLSurfaceView view, boolean value) {
        view.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE){

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                }
                return false;
            }
        });
    }
}