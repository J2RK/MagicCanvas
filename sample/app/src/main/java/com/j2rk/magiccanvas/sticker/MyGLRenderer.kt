package com.j2rk.magiccanvas.sticker

import android.graphics.Color
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;
import androidx.core.graphics.red
import androidx.core.graphics.toColor

/**
 * Provides drawing instructions for a GLSurfaceView object. This class
 * must override the OpenGL ES drawing lifecycle methods:
 *
 *  * [android.opengl.GLSurfaceView.Renderer.onSurfaceCreated]
 *  * [android.opengl.GLSurfaceView.Renderer.onDrawFrame]
 *  * [android.opengl.GLSurfaceView.Renderer.onSurfaceChanged]
 *
 */
class MyGLRenderer : GLSurfaceView.Renderer {
    //    private var mTriangle: Triangle? = null
//    private var mSquare: Square? = null
    private var mCube: Triangle? = null

    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private val mMVPMatrix = FloatArray(16)
    private val mProjectionMatrix = FloatArray(16)
    private val mViewMatrix = FloatArray(16)
    private val mRotationMatrix = FloatArray(16)
    private val mTranslationMatrix = FloatArray(16)
    private val mScaleMatrix = FloatArray(16)
    /**
     * Returns the rotation angle of the triangle shape (mTriangle).
     *
     * @return - A float representing the rotation angle.
     */
    /**
     * Sets the rotation angle of the triangle shape (mTriangle).
     */
    var angle = 0f
    var x = 0.0f
    var y = 0.0f
    var scale = 1.0f

    var width = 0
    var height = 0
    private var ratio = 0f

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        // Set the background frame color
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
        mCube = Triangle()
    }

    override fun onDrawFrame(unused: GL10) {
        val transform = FloatArray(16)
        Matrix.setIdentityM(transform, 0)

        val scratch = FloatArray(16)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)
        Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0)
        Matrix.setRotateM(mRotationMatrix, 0, angle, 1.0f, 1.0f, 1.0f)
        Matrix.multiplyMM(transform, 0, transform, 0, mRotationMatrix, 0)

        Matrix.setIdentityM(mScaleMatrix, 0)
        Matrix.scaleM(mScaleMatrix, 0, scale, scale, scale)
        Matrix.multiplyMM(transform, 0, transform, 0, mScaleMatrix, 0)

        Matrix.setIdentityM(mTranslationMatrix, 0)
        val normalX = normalizePosition(x, width.toFloat()) * ratio
        val normalY = normalizePosition(y, height.toFloat())

        Log.d("renderer", "x:$x / width: $width / normalX : $normalX")
        Log.d("renderer", "y:$y / height: $height / normalY : $normalY")
        Matrix.translateM(mTranslationMatrix, 0, -normalX, -normalY, 0f)
        Matrix.multiplyMM(transform, 0, transform, 0, mTranslationMatrix, 0)
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, transform, 0)
        mCube?.draw(scratch)
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        ratio = width.toFloat() / height
        this.width = width
        this.height = height

        x = (width / 2) * ratio
        y = (height / 2) * ratio

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
    }

    private fun normalizePosition(position: Float, maxPosition: Float): Float {
        return 2 * position / maxPosition - 1
    }

    fun setColor(r:Int, g:Int, b:Int, a:Int) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mCube?.let {
//                val r = 0.2f//color.red() / 255
//                val g = 0.4f//color.green() / 255
//                val b = 0.6f//color.blue() / 255
//                val a = 1.0f//color.alpha() / 255
                Log.d("renderer", "rgb r:$r g:$g b:$b a:$a")
                it.color[0] = r.toFloat() / 255f
                it.color[1] = g.toFloat() / 255f
                it.color[2] = b.toFloat() / 255f
                it.color[3] = a.toFloat() / 255f
            }
        } else {
//            mCube?.color = floatArrayOf(color)
        }
    }

    companion object {
        private const val TAG = "MyGLRenderer"

        /**
         * Utility method for compiling a OpenGL shader.
         *
         *
         * **Note:** When developing shaders, use the checkGlError()
         * method to debug shader coding errors.
         *
         * @param type - Vertex or fragment shader type.
         * @param shaderCode - String containing the shader code.
         * @return - Returns an id for the shader.
         */
        fun loadShader(type: Int, shaderCode: String?): Int {
            val shader = GLES20.glCreateShader(type)
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)
            return shader
        }

        /**
         * Utility method for debugging OpenGL calls. Provide the name of the call
         * just after making it:
         *
         * <pre>
         * mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
         * MyGLRenderer.checkGlError("glGetUniformLocation");</pre>
         *
         * If the operation is not successful, the check throws an error.
         *
         * @param glOperation - Name of the OpenGL call to check.
         */
        fun checkGlError(glOperation: String) {
            var error: Int
            while (GLES20.glGetError().also { error = it } != GLES20.GL_NO_ERROR) {
                Log.e(TAG, "$glOperation: glError $error")
                throw RuntimeException("$glOperation: glError $error")
            }
        }
    }
}