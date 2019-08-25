package com.barannikdima.textrecognizer.utils

import android.app.Activity
import android.graphics.*
import android.net.Uri
import android.provider.MediaStore
import androidx.exifinterface.media.ExifInterface

object BitmapUtils {

    private val paint = Paint().apply {
        color = Color.GREEN
        alpha = 100
        style = Paint.Style.FILL
    }

    fun drawRects(bitmap: Bitmap, rects: List<Rect>) =
            bitmap.copy(Bitmap.Config.ARGB_8888, true).apply {
                val canvas = Canvas(this)
                rects.forEach { canvas.drawRect(it, paint) }
            }

    fun loadRotatedBitmap(uri: Uri, activity: Activity): Bitmap {
        ExifInterface(activity.contentResolver.openInputStream(uri))
        val originImage = MediaStore.Images.Media.getBitmap(activity.contentResolver, uri)
        return originImage
    }
}