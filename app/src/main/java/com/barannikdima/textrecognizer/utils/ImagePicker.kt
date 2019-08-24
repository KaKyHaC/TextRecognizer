package com.barannikdima.textrecognizer.utils

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.File



object ImagePicker {
    private const val CAMERA_REQUEST = 1000

    private val storageDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES)

    private val file = File(storageDir, "image_to_recognize.jpg").apply {
        createNewFile()
    }

    private val fileUri = Uri.fromFile(file)

    fun startCameraActivity(activity: Activity) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            resolveActivity(activity.packageManager)?.let {
                activity.startActivityForResult(this, CAMERA_REQUEST)
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            Log.d("ImagePicker", "bitmap = $bitmap")
        }
    }
}