package com.example.colorpicker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.colorpicker.databinding.ActivityMainBinding
import yuku.ambilwarna.AmbilWarnaDialog

class MainActivity : AppCompatActivity() {

    companion object{
        private const val RGB_VIEW = "RGB_VEW"
        private const val COLOR_PICKER_VIEW = "COLOR_PICKER_VIEW"
    }

    private var currentVisibleView: String = RGB_VIEW

    private var binding: ActivityMainBinding? = null

    private var mDefaultColor = 0
    private var red = 0
    private var green = 0
    private var blue = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        makeVisibleRGBView()
        binding?.rgViews?.setOnCheckedChangeListener { _, checkedId: Int ->
            print(checkedId)
            print(R.id.rbRGB)
            if (checkedId == R.id.rbRGB) {
                makeVisibleRGBView()
            } else {
                makeVisibleColorPickerView()
            }
        }

        binding?.pickColorButton?.setOnClickListener{
            openColorPickerDialogue()
        }

        binding?.backgroundBtn?.setOnClickListener {

            red = binding?.redInput?.text.toString().toInt(16)
            green = binding?.greenInput?.text.toString().toInt(16)
            blue = binding?.blueInput?.text.toString().toInt(16)

            val color = Color.rgb(red, green, blue)
            if (binding?.pickColorButton?.visibility==View.GONE) {
                if (binding!!.redInput.text.isNotEmpty()){
                    red=binding!!.redInput.text.toString().toInt()
                }
                if (binding!!.blueInput.text.isNotEmpty()){
                    blue=binding!!.blueInput.text.toString().toInt()
                }
                if (binding!!.greenInput.text.isNotEmpty()){
                    green=binding!!.greenInput.text.toString().toInt()
                }

                val intent=Intent(this,BackgroundActivity::class.java)
                startActivity(intent)

            }else{

                val intent=Intent(this,BackgroundActivity::class.java)
                intent.putExtra("color",mDefaultColor)
                startActivity(intent)

            }
        }


    }

    private fun makeVisibleRGBView() {
        currentVisibleView = RGB_VIEW
        binding?.rgbView?.visibility = View.VISIBLE
        binding?.colorPickerView?.visibility = View.GONE
    }

    private fun makeVisibleColorPickerView() {
        currentVisibleView = COLOR_PICKER_VIEW
        binding?.rgbView?.visibility = View.GONE
        binding?.colorPickerView?.visibility = View.VISIBLE
    }

    fun openColorPickerDialogue() {

        val colorPickerDialogue = AmbilWarnaDialog(this, mDefaultColor,
            object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {
                }

                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    mDefaultColor = color

                    binding?.previewSelectedColor?.setBackgroundColor(mDefaultColor)
                }
            })
        colorPickerDialogue.show()
    }

}