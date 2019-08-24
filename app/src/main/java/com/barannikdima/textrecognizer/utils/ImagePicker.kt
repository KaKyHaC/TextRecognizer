package com.barannikdima.textrecognizer.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.File

object ImagePicker {
    private const val CAMERA_INTENT = 1000

    private val storageDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES)

    private val file = File(storageDir, "image_to_recognize.jpg")

    private val fileUri = Uri.fromFile(file)

    fun startCameraActivity(activity: Activity) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            resolveActivity(activity.packageManager)?.let {
                activity.startActivityForResult(this, CAMERA_INTENT)
            }
        }
    }
}