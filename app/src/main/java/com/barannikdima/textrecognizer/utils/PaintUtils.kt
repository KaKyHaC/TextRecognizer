package com.barannikdima.textrecognizer.utils

import android.graphics.*

object PaintUtils {

    private val paint = Paint().apply {
        color = Color.GREEN
        alpha = 100
        style = Paint.Style.FILL
    }

    fun printRect(bitmap: Bitmap, rect: Rect) =
            bitmap.copy(Bitmap.Config.ARGB_8888, true).apply {
                Canvas(this).drawRect(rect, paint)
            }

}