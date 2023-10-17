package com.example.colorpicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.colorpicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        private const val RGB_VIEW = "RGB_VEW"
        private const val COLOR_PICKER_VIEW = "COLOR_PICKER_VIEW"
    }

    private var currentVisibleView: String = RGB_VIEW

    private var binding: ActivityMainBinding? = null

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


    }

    private fun setRGBColor(){
        val redColor = binding?.redInput?.text.toString().toInt(16)
        val greenColor = binding?.greenInput?.text.toString().toInt(16)
        val blueColor = binding?.blueInput?.text.toString().toInt(16)


        binding?.backgroundBtn?.setOnClickListener {
            setRGBColor()
            val intent = Intent(this, BackgroundActivity::class.java)
            startActivity(intent)
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
}