package com.barannikdima.textrecognizer.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
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

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            onFileSelectedListener(file)
        } else if (requestCode == GALLEY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { getPath(it)?.let { onFileSelectedListener(File(it)) } }
        }
    }

    fun getPath(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = activity.contentResolver.query(uri, projection, null, null, null)
        if (cursor == null) return null
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val columnIndex = cursor.getColumnIndex(projection[0])
        val filePath = cursor.getString(columnIndex)
        cursor.close()
        return cursor.getString(column_index)
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1000
        private const val GALLEY_REQUEST_CODE = 1001
    }
}