package com.j2rk.magiccanvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class MyPaintView : View {
    private lateinit var mCanvas: Canvas
    private lateinit var mBitmap: Bitmap
    private lateinit var mPaint: Paint
    private lateinit var mPath: Path
    private var changed = false
    private var lastX = 0f
    private var lastY = 0f
    private var mCurveEndX = 0f
    private var mCurveEndY = 0f
    private var mInvalidateExtraBorder = 10

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        mPaint = Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 3.0f
        }
        mPath = Path()
        lastX = -1f
        lastY = -1f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val img = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas().apply {
            setBitmap(img)
            drawColor(Color.WHITE)
        }
        mBitmap = img
        mCanvas = canvas
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(mBitmap, 0f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> { // 화면을 눌렀을 때
                touchDown(event)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> { // 화면을 누른채로 움직일 때
                processMove(event)
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> { // 화면에서 손을 뗐을 때
                changed = true
                processMove(event) // ACTION_UP과 ACTION_MOVE는 기능만 살짝 다르고 전체적인 의미는 같은 함수
                invalidate()
                mPath.reset()
                return true
            }
        }
        // 터치 이벤트 값을 false로 설정하면 한 번 터치했을 때만 실행되며, true로 하면 터치한 상태로 드래그를 하면 연속적으로 실행된다
        return false
    }

    private fun processMove(event: MotionEvent): Rect {
        val x = event.x
        val y = event.y
        val dx = abs(x - lastX)
        val dy = abs(y - lastY)
        val mInvalidateRect = Rect()
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) { // 단순 터치인지 선을 그릴 것인지를 판단
            val border = mInvalidateExtraBorder
            mInvalidateRect[mCurveEndX.toInt() - border, mCurveEndY.toInt() - border, mCurveEndX.toInt() + border] = mCurveEndY.toInt() + border
            mCurveEndX = (x + lastX) / 2
            mCurveEndY = (y + lastY) / 2
            mPath.quadTo(lastX, lastY, mCurveEndX, mCurveEndY)
            mInvalidateRect.union(
                lastX.toInt() - border,
                lastY.toInt() - border,
                lastX.toInt() + border,
                lastY.toInt() + border
            )
            mInvalidateRect.union(
                mCurveEndX.toInt() - border,
                mCurveEndY.toInt() - border,
                mCurveEndX.toInt() + border,
                mCurveEndY.toInt() + border
            )
            lastX = x
            lastY = y
            mCanvas.drawPath(mPath, mPaint)
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
        mInvalidateRect[x.toInt() - border, y.toInt() - border, x.toInt() + border] = y.toInt() + border
        mCurveEndX = x
        mCurveEndY = y
        mCanvas.drawPath(mPath, mPaint)
        return mInvalidateRect
    }

    fun setStrokeWidth(width: Int) {
        mPaint.strokeWidth = width.toFloat()
    }

    fun setColor(color: Int) {
        mPaint.color = color
    }

    fun setCap(cap: Int) {
        when (cap) {
            0 -> mPaint.strokeCap = Paint.Cap.BUTT
            1 -> mPaint.strokeCap = Paint.Cap.ROUND
            2 -> mPaint.strokeCap = Paint.Cap.SQUARE
        }
    }

    companion object {
        const val TOUCH_TOLERANCE = 8f // 터치했을 때의 범위를 나타내는 상수
    }
}