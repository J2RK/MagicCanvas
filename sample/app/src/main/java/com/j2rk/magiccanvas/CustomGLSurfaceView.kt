package com.j2rk.magiccanvas

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent

class CustomGLSurfaceView(context: Context?) : GLSurfaceView(context) {

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // TODO
        return super.onTouchEvent(event)
    }
}