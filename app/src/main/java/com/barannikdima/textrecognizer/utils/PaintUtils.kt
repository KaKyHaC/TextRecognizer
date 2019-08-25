package com.barannikdima.textrecognizer.utils

import android.graphics.*

object PaintUtils {

    fun printRect(bitmap: Bitmap, rect: Rect): Bitmap {
        val bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            color = Color.GREEN
            alpha = 100
        }
        canvas.drawRect(rect, paint)
        return bitmap
    }
}