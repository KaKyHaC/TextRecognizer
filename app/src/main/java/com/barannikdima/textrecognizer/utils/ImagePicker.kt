package com.barannikdima.textrecognizer.utils

import android.app.Activity
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File


object ImagePicker {

    private val file by lazy {
        val directory = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)
        File(directory, "image_to_recognize.jpg").apply {
            mkdir()
            createNewFile()
        }
    }

    fun startCameraActivity(activity: Activity, requestCode: Int) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            val uri = FileProvider.getUriForFile(activity, "com.barannikdima.textrecognizer.provider", file)
            putExtra(MediaStore.EXTRA_OUTPUT, uri)
            activity.startActivityForResult(this, requestCode)
        }
    }

    fun startGalleryActivity(activity: Activity, requestCode: Int) {
        Intent(Intent.ACTION_GET_CONTENT).apply {
            setType("image/*");
            activity.startActivityForResult(Intent.createChooser(this, "Select Picture"), requestCode);
        }
    }

}