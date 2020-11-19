package com.j2rk.magiccanvas.doodling

import android.annotation.SuppressLint
import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.MotionEvent
import com.j2rk.magiccanvas.doodling.paint.PaintType

class CustomGLSurface(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs) {

    private val renderer: CustomGLRenderer


    init {
        setEGLContextClientVersion(2)

        // Set the Renderer for drawing on the GLSurfaceView
        renderer = CustomGLRenderer(this)
        setRenderer(renderer)

        // Render the view only when there is a change in the drawing data
        // Might need to change for the painting app
        renderMode = RENDERMODE_CONTINUOUSLY
    }

    fun setPaintType(penType: PaintType) {
        renderer.paintType = penType
    }

    fun clearAll() {
        renderer.clearAll()
    }

    override fun onPause() {
        super.onPause()
        renderer.onPause()
    }

    override fun onResume() {
        super.onResume()
        renderer.onResume()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> renderer.processEventMove(event)
            MotionEvent.ACTION_DOWN -> renderer.processEventDown(event)
            MotionEvent.ACTION_UP -> renderer.processEventUp()
        }
        return true
    }
}
