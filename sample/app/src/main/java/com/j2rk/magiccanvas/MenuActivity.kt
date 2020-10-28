package com.j2rk.magiccanvas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.j2rk.magiccanvas.databinding.ActivityMenuBinding
import com.j2rk.magiccanvas.sticker.StickerActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.butMain.setOnClickListener {
            startActivity(Intent(this, MagicCanvasActivity::class.java))
        }

        binding.butSticker.setOnClickListener {
            startActivity(Intent(this, StickerActivity::class.java))
        }
    }
}