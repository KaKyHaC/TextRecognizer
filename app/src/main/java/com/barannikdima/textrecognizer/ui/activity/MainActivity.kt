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
        if (PermissionUtils.tryWithRequestPermissions(this, PERMISSION_REQUEST_CODE))
            ImagePicker.startCameraActivity(this, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE && PermissionUtils.tryWithPermissions(this))
            ImagePicker.startCameraActivity(this, CAMERA_REQUEST_CODE)
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 200
        private const val CAMERA_REQUEST_CODE = 1000
    }
}
