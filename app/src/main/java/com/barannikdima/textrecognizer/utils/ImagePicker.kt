package com.barannikdima.textrecognizer.utils

import android.app.Activity
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
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
            val uri = FileProvider.getUriForFile(activity, "com.barannikdima.textrecognizer.provider", file)
            putExtra(MediaStore.EXTRA_OUTPUT, uri)
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