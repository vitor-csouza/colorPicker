package com.example.colorpicker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.colorpicker.databinding.ActivityBackgroundBinding

class BackgroundActivity : AppCompatActivity() {


    private var binding: ActivityBackgroundBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBackgroundBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val color = intent.getIntExtra("color", 0)
        if (color != 0) {
            binding?.root?.setBackgroundColor(color)
        }

        binding?.btnBack?.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}