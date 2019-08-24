package com.barannikdima.textrecognizer.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        gallery_btn.setOnClickListener(::startGalleryActivity)
    }

    private fun startCameraActivity(view: View) {
        if (PermissionUtils.tryWithRequestPermissions(this, CAMERA_PERMISSION_REQUEST_CODE))
            ImagePicker.startCameraActivity(this)
    }

    private fun startGalleryActivity(view: View) {
        if (PermissionUtils.tryWithRequestPermissions(this, GALLERY_PERMISSION_REQUEST_CODE))
            ImagePicker.startGalleryActivity(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("main", "res $data")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && PermissionUtils.tryWithPermissions(this))
            ImagePicker.startCameraActivity(this)
        else if (requestCode == GALLERY_PERMISSION_REQUEST_CODE && PermissionUtils.tryWithPermissions(this))
            ImagePicker.startGalleryActivity(this)
    }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 200
        private const val GALLERY_PERMISSION_REQUEST_CODE = 201

    }
}
