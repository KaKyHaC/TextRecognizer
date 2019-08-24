package com.barannikdima.textrecognizer.utils

import android.app.Activity
import android.content.Intent
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File


class ImagePicker(private val activity: Activity) {

    var onFileSelectedListener = { _: File -> }

    private val file by lazy {
        val directory = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)
        File(directory, "image_to_recognize.jpg").apply {
            mkdir()
            createNewFile()
        }
    }

    fun startCameraActivity() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            val authority = "com.barannikdima.textrecognizer.provider"
            val uri = FileProvider.getUriForFile(activity, authority, file)
            putExtra(MediaStore.EXTRA_OUTPUT, uri)
            activity.startActivityForResult(this, CAMERA_REQUEST_CODE)
        }
    }

    fun startGalleryActivity() {
        Intent(Intent.ACTION_GET_CONTENT).apply {
            setType("image/*");
            val chooser = Intent.createChooser(this, "Select Picture")
            activity.startActivityForResult(chooser, GALLEY_REQUEST_CODE);
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){

    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1000
        private const val GALLEY_REQUEST_CODE = 1001
    }
}