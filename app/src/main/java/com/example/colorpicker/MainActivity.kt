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
        setContentView(R.layout.activity_main)


        makeVisibleRGB()
        binding?.rgColors?.setOnCheckedChangeListener { _, checkedId: Int ->
            if(checkedId==R.id.rgbBtn){
                makeVisibleRGB()
            } else {
                makeVisibleColorPicker()
            }
        }


    }

    private fun setRGBColor(){
        val redColor = binding?.redInput?.text.toString().toInt()
        val greenColor = binding?.greenInput?.text.toString().toInt()
        val blueColor = binding?.blueInput?.text.toString().toInt()

        binding?.backgroundBtn?.setOnClickListener {
            val intent = Intent(this, BackgroundActivity::class.java)
            startActivity(intent)
        }

    }

    private fun makeVisibleColorPicker(){
        currentVisibleView = COLOR_PICKER_VIEW
        binding?.rgbView?.visibility = View.GONE
        binding?.colorPickerView?.visibility = View.VISIBLE
        binding?.redInput?.text!!.clear()
        binding?.greenInput?.text!!.clear()
        binding?.blueInput?.text!!.clear()
    }

    private fun makeVisibleRGB(){
        currentVisibleView = RGB_VIEW
        binding?.rgbView?.visibility = View.VISIBLE
        binding?.colorPickerView?.visibility = View.GONE
    }
}