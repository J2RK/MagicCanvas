package com.j2rk.magiccanvas.feature.sticker

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.MotionEvent


class MyGLSurfaceView : GLSurfaceView {
    private val mRenderer: MyGLRenderer
    private val TOUCH_SCALE_FACTOR = 180.0f / 320
    private var mPreviousX = 0f
    private var mPreviousY = 0f

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onTouchEvent(e: MotionEvent): Boolean {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
        val x = e.x
        val y = e.y
        when (e.action) {
            MotionEvent.ACTION_MOVE -> {
//                var dx = x - mPreviousX
//                var dy = y - mPreviousY
//                // reverse direction of rotation above the mid-line
//                if (y > height / 2) {
//                    dx = dx * -1
//                }
//                // reverse direction of rotation to left of the mid-line
//                if (x < width / 2) {
//                    dy = dy * -1
//                }
                mRenderer.x = x

                mRenderer.y = y
//                mRenderer.angle = mRenderer.angle +
//                        (dx + dy) * TOUCH_SCALE_FACTOR // = 180.0f / 320
                requestRender()
            }
        }
        mPreviousX = x
        mPreviousY = y
        return true
    }

    fun setDegree(degree: Float) {
        mRenderer.angle = degree
        requestRender()
    }

    fun setScale(scale: Float) {
//        mRenderer.scale = scale
        requestRender()
    }

    fun setColor(r: Int, g: Int, b: Int, a: Int) {
        mRenderer.setColor(r, g, b, a)
        requestRender()
    }

    init {
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2)
        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = MyGLRenderer()
//        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val display = windowManager.defaultDisplay
//        val point = Point()
//        display.getSize(point)
//        mRenderer.x = point.x.toFloat()
//        mRenderer.y = point.y.toFloat()
        setRenderer(mRenderer)
        // Render the view only when there is a change in the drawing data
//        renderMode = RENDERMODE_WHEN_DIRTY
    }
}