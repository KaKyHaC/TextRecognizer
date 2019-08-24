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

    private val imagePicker by lazy { ImagePicker(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        camera_btn.setOnClickListener(::startCameraActivity)
        gallery_btn.setOnClickListener(::startGalleryActivity)
        imagePicker.onUriSelectedListener = {
            startActivity(RecognizeActivity.createIntent(this, it))
        }
    }

    private fun startCameraActivity(view: View) {
        if (PermissionUtils.checkPermissions(this))
            imagePicker.startCameraActivity()
        else
            PermissionUtils.requestPermissions(this, CAMERA_PERMISSION_REQUEST_CODE)
    }

    private fun startGalleryActivity(view: View) {
        if (PermissionUtils.checkPermissions(this))
            imagePicker.startGalleryActivity()
        else
            PermissionUtils.requestPermissions(this, GALLERY_PERMISSION_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imagePicker.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && PermissionUtils.checkPermissions(this))
            imagePicker.startCameraActivity()
        else if (requestCode == GALLERY_PERMISSION_REQUEST_CODE && PermissionUtils.checkPermissions(this))
            imagePicker.startGalleryActivity()
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 200
        private const val GALLERY_PERMISSION_REQUEST_CODE = 201
    }
}
