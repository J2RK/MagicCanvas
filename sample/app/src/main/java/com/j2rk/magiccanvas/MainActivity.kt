package com.j2rk.magiccanvas

import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import yuku.ambilwarna.AmbilWarnaDialog


class MainActivity : AppCompatActivity() {
    var view: MyPaintView? = null
    var tColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view = MyPaintView(this)
        val container = findViewById<LinearLayout>(R.id.container)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        container.addView(view, params)
        val rBtn1 = findViewById<RadioButton>(R.id.radioButton)
        val rBtn2 = findViewById<RadioButton>(R.id.radioButton2)
        val rBtn3 = findViewById<RadioButton>(R.id.radioButton3)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton -> view!!.setCap(0)
                R.id.radioButton2 -> view!!.setCap(1)
                R.id.radioButton3 -> view!!.setCap(2)
            }
        }
        val btn = findViewById<Button>(R.id.colorPickerButton)
        val btn2 = findViewById<Button>(R.id.thickPickerButton)
        btn.setOnClickListener { openColorPicker() }
        btn2.setOnClickListener { show() }
    }

    private fun show() {
        val editText = EditText(this)
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        AlertDialog.Builder(this)
            .setTitle("AlertDialog Title")
            .setMessage("굵기 입력")
            .setView(editText)
            .setPositiveButton("입력") { dialog, which -> view!!.setStrokeWidth(editText.text.toString().toInt()) }
            .setNegativeButton("취소") { dialog, which -> }
            .show()
    }

    private fun openColorPicker() {
        val colorPicker = AmbilWarnaDialog(this, tColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {}
                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    Toast.makeText(applicationContext, "" + tColor, Toast.LENGTH_LONG).show()
                    view?.setColor(color)
                }
            })
        colorPicker.show()
    }
}

