package com.j2rk.magiccanvas.feature.sticker

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.j2rk.magiccanvas.R
import com.j2rk.magiccanvas.databinding.ActivityStickerBinding

/**
 * https://android.googlesource.com/platform/development/+/master/samples/OpenGL?autodive=0
 * */

class StickerActivity : AppCompatActivity() {
    private lateinit var mGLView: MyGLSurfaceView
    private lateinit var binding: ActivityStickerBinding

    @RequiresApi(Build.VERSION_CODES.O)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        binding = ActivityStickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        mGLView = findViewById(R.id.oglView)
        binding.sbDegree.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                mGLView.setDegree(p1.toFloat())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.sbDegree.bringToFront()
        binding.sbScale.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                mGLView.setScale(p1.toFloat() / 100)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        binding.sbScale.bringToFront()

        binding.butColor.setOnClickListener {
            ColorPickerDialog
                .Builder(this)                    // Pass Activity Instance
                .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                .setDefaultColor(Color.BLUE)            // Pass Default Color
                .setColorListener { color, colorHex ->
                    // Handle Color Selection
                    Log.d(StickerActivity::class.java.simpleName, "rgb $color / $colorHex")
                    Color.valueOf(Color.parseColor(colorHex))
                    val r = Color.red(color)
                    val g = Color.green(color)
                    val b = Color.blue(color)
                    val a = Color.alpha(color)

                    binding.oglView.setColor(r, g, b, a)
                }
                .show()
        }
    }

    override fun onPause() {
        super.onPause()
        mGLView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mGLView.onResume()
    }
}