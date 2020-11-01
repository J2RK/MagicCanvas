package com.j2rk.magiccanvas

import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import yuku.ambilwarna.AmbilWarnaDialog


class MainActivity : AppCompatActivity() {
    lateinit var view: MyPaintView
    var tColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view = MyPaintView(this)
        val container = findViewById<LinearLayout>(R.id.container)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        container.addView(view, params)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton -> view.setCap(0)
                R.id.radioButton2 -> view.setCap(1)
                R.id.radioButton3 -> view.setCap(2)
            }
        }
        val colorPickerButton = findViewById<Button>(R.id.colorPickerButton)
        val thickPickerButton = findViewById<Button>(R.id.thickPickerButton)
        colorPickerButton.setOnClickListener { openColorPicker() }
        thickPickerButton.setOnClickListener { show() }
    }

    private fun show() {
        val editText = EditText(this)
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        AlertDialog.Builder(this)
            .setTitle("AlertDialog Title")
            .setMessage("굵기 입력")
            .setView(editText)
            .setPositiveButton("입력") { dialog, which -> view.setStrokeWidth(editText.text.toString().toInt()) }
            .setNegativeButton("취소") { dialog, which -> dialog.dismiss()}
            .show()
    }

    private fun openColorPicker() {
        val colorPicker = AmbilWarnaDialog(this, tColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {}
                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    view.setColor(color)
                    tColor = color
                }
            })
        colorPicker.show()
    }
}

