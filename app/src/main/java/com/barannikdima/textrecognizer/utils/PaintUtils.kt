package com.barannikdima.textrecognizer.utils

import android.graphics.*

object PaintUtils {

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

}