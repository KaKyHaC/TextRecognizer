package com.barannikdima.textrecognizer.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.File


object ImagePicker {
    private const val CAMERA_REQUEST = 1000

    private val file by lazy {
        val directory = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)
        File(directory, "image_to_recognize.jpg").apply { createNewFile() }
    }

    fun startCameraActivity(activity: Activity) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file))
            resolveActivity(activity.packageManager)?.let {
                activity.startActivityForResult(this, CAMERA_REQUEST)
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
        }
    }
}