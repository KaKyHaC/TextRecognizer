package com.barannikdima.textrecognizer.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barannikdima.textrecognizer.R
import com.barannikdima.textrecognizer.utils.ImagePicker
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        camera_btn.setOnClickListener { ImagePicker.startCameraActivity(this) }
    }

}
