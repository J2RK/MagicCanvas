package com.j2rk.magiccanvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class MyPaintView : View {
    var changed = false
    var mCanvas: Canvas? = null
    var mBitmap: Bitmap? = null
    var mPaint: Paint? = null
    var lastX = 0f
    var lastY = 0f
    var mPath = Path()
    var mCurveEndX = 0f
    var mCurveEndY = 0f
    var mInvalidateExtraBorder = 10

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPaint?.apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 3.0f
        }
        lastX = -1f
        lastY = -1f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val img = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas()
        canvas.setBitmap(img)
        canvas.drawColor(Color.WHITE)
        mBitmap = img
        mCanvas = canvas
    }

    override fun onDraw(canvas: Canvas) {
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap!!, 0f, 0f, null)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        val rect: Rect
        when (action) {
            MotionEvent.ACTION_UP -> {
                changed = true
                rect = touchUp(event)
                invalidate(rect)
                mPath.rewind()
                return true
            }
            MotionEvent.ACTION_DOWN -> {
                rect = touchDown(event)
                invalidate(rect)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                rect = touchMove(event)
                invalidate(rect)
                return true
            }
        }
        return false
    }

    private fun touchMove(event: MotionEvent): Rect {
        return processMove(event)
    }

    private fun processMove(event: MotionEvent): Rect {
        val x = event.x
        val y = event.y
        val dx = abs(x - lastX)
        val dy = abs(y - lastY)
        val mInvalidateRect = Rect()
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            val border = mInvalidateExtraBorder
            mInvalidateRect[mCurveEndX.toInt() - border, mCurveEndY.toInt() - border, mCurveEndX.toInt() + border] =
                mCurveEndY.toInt() + border
            mCurveEndX = (x + lastX) / 2
            val cx = mCurveEndX
            mCurveEndY = (y + lastY) / 2
            val cy = mCurveEndY
            mPath.quadTo(lastX, lastY, cx, cy)
            mInvalidateRect.union(
                lastX.toInt() - border,
                lastY.toInt() - border,
                lastX.toInt() + border,
                lastY.toInt() + border
            )
            mInvalidateRect.union(
                cx.toInt() - border,
                cy.toInt() - border,
                cx.toInt(),
                cy.toInt() + border
            )
            lastX = x
            lastY = y
            mCanvas!!.drawPath(mPath, mPaint!!)
        }
        return mInvalidateRect
    }

    private fun touchDown(event: MotionEvent): Rect {
        val x = event.x
        val y = event.y
        lastX = x
        lastY = y
        val mInvalidateRect = Rect()
        mPath.moveTo(x, y)
        val border = mInvalidateExtraBorder
        mInvalidateRect[x.toInt() - border, y.toInt() - border, x.toInt() + border] =
            y.toInt() + border
        mCurveEndX = x
        mCurveEndY = y
        mCanvas!!.drawPath(mPath, mPaint!!)
        return mInvalidateRect
    }

    fun setStrokeWidth(width: Int) {
        mPaint?.strokeWidth = width.toFloat()
    }

    private fun touchUp(event: MotionEvent): Rect {
        return processMove(event)
    }

    fun setColor(color: Int) {
        mPaint?.color = color
    }

    fun setCap(cap: Int) {
        when (cap) {
            0 -> mPaint?.strokeCap = Paint.Cap.BUTT
            1 -> mPaint?.strokeCap = Paint.Cap.ROUND
            2 -> mPaint?.strokeCap = Paint.Cap.SQUARE
        }
    }

    companion object {
        const val TOUCH_TOLERANCE = 8f
    }
}