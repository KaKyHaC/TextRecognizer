package com.barannikdima.textrecognizer.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File


class ImagePicker(private val activity: Activity) {

    var onUriSelectedListener = { _: Uri -> }

    private val file by lazy {
        val directory = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)
        File(directory, "image_to_recognize.jpg").apply {
            mkdir()
            createNewFile()
        }
    }

    private val uri by lazy {
        val authority = "com.barannikdima.textrecognizer.provider"
        FileProvider.getUriForFile(activity, authority, file)
    }


    fun startCameraActivity() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
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

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            onUriSelectedListener(uri)
        } else if (requestCode == GALLEY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { onUriSelectedListener(it) }
        }
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1000
        private const val GALLEY_REQUEST_CODE = 1001
    }
}