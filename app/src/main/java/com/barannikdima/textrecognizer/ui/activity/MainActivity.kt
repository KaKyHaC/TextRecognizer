package com.barannikdima.textrecognizer.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.barannikdima.textrecognizer.R
import com.barannikdima.textrecognizer.utils.ImagePicker
import com.barannikdima.textrecognizer.utils.PermissionUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        camera_btn.setOnClickListener(::startCameraActivity)
    }

    fun startCameraActivity(view: View) {
        if (PermissionUtils.tryWithRequestPermissions(this))
            ImagePicker.startCameraActivity(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ImagePicker.onActivityResult(requestCode, requestCode, data)
    }
}
