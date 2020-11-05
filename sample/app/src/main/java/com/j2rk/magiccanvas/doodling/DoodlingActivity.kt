package com.j2rk.magiccanvas.doodling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.j2rk.magiccanvas.databinding.ActivityDoodlingBinding


import com.j2rk.magiccanvas.doodling.paint.PaintType


class DoodlingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDoodlingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.penButton.setOnClickListener { binding.glSurface.setPaintType(PaintType.PEN) }
        binding.clearButton.setOnClickListener { binding.glSurface.clearAll() }
    }
}