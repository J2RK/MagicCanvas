package com.j2rk.magiccanvas.feature.doodling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.j2rk.magiccanvas.databinding.ActivityCanvasBinding

import com.j2rk.magiccanvas.feature.doodling.paint.PaintType


class MagicCanvasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.penButton.setOnClickListener { binding.glSurface.setPaintType(PaintType.PEN) }
        binding.markerButton.setOnClickListener { binding.glSurface.setPaintType(PaintType.MARKER) }
        binding.brushButton.setOnClickListener { binding.glSurface.setPaintType(PaintType.BRUSH) }
        binding.eraserButton.setOnClickListener { binding.glSurface.setPaintType(PaintType.ERASER) }
        binding.clearButton.setOnClickListener { binding.glSurface.clearAll() }
    }
}