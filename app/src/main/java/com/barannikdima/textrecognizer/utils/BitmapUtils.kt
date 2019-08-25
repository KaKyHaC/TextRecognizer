package com.barannikdima.textrecognizer.utils

import android.app.Activity
import android.graphics.*
import android.net.Uri
import android.provider.MediaStore
import androidx.exifinterface.media.ExifInterface


object BitmapUtils {

    private val fillPaint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
        alpha = 100
    }

    private val strokePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }

    fun strokeRects(bitmap: Bitmap, rects: List<Rect>) =
        bitmap.copy(Bitmap.Config.ARGB_8888, true).apply {
            val canvas = Canvas(this)
            rects.forEach { canvas.drawRect(it, strokePaint) }
        }

    fun fillRects(bitmap: Bitmap, rects: List<Rect>) =
        bitmap.copy(Bitmap.Config.ARGB_8888, true).apply {
            val canvas = Canvas(this)
            rects.forEach { canvas.drawRect(it, fillPaint) }
        }

    fun loadRotatedBitmap(uri: Uri, activity: Activity): Bitmap {
        val originImage = MediaStore.Images.Media.getBitmap(activity.contentResolver, uri)
        activity.contentResolver.openInputStream(uri)?.let {
            val exifInterface = ExifInterface(it)
            val matrix = Matrix().apply { postRotate(exifInterface.rotationDegrees.toFloat()) }
            return Bitmap.createBitmap(
                originImage,
                0,
                0,
                originImage.getWidth(),
                originImage.getHeight(),
                matrix,
                true
            )
        }
        return originImage
    }
}