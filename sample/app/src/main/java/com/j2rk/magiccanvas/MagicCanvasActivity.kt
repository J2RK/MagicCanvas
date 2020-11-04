package com.j2rk.magiccanvas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.j2rk.magiccanvas.databinding.ActivityCanvasBinding

import com.j2rk.magiccanvas.databinding.ActivityMainBinding
import com.j2rk.magiccanvas.paint.PaintType


class MagicCanvasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.penButton.setOnClickListener { binding.glSurface.setPaintType(PaintType.PEN) }
        binding.swipeMeshButton.setOnClickListener { binding.glSurface.setPaintType(PaintType.SWIPE_MESH) }
        binding.eraserButton.setOnClickListener { binding.glSurface.setPaintType(PaintType.ERASER) }
        binding.clearButton.setOnClickListener { binding.glSurface.clearAll() }
    }
}