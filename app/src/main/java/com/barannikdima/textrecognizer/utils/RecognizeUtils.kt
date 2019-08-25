package com.barannikdima.textrecognizer.utils

import android.graphics.Bitmap
import android.graphics.Rect
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText

class RecognizeUtils {

    private val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

    private var recognizeResult: Task<FirebaseVisionText>? = null

    fun recognize(bitmap: Bitmap, onFound: RectListCallback) {
        recognizeResult = null
        val visionImage = FirebaseVisionImage.fromBitmap(bitmap)
        recognizeResult = detector.processImage(visionImage)
            .addOnSuccessListener { onFound(it.textBlocks.map { it.boundingBox }.filterNotNull()) }
            .addOnFailureListener { Log.d("RecognizeUtils", it.toString()) }
    }

    fun find(word: String, onFound: RectListCallback) {
        if (recognizeResult?.isSuccessful.isTrue()) {
            recognizeResult?.result?.let { find(it, word, onFound) }
        } else {
            recognizeResult?.addOnSuccessListener { find(it, word, onFound) }
        }
    }

    private fun find(
        firebaseVisionText: FirebaseVisionText,
        word: String,
        onFound: RectListCallback
    ) {
        val text = word.toLowerCase()
        val result = mutableListOf<Rect>()
        firebaseVisionText.textBlocks
            .forEach {
                it.lines.forEach {
                    it.elements.forEach {
                        if (it.text.toLowerCase().contains(text))
                            it.boundingBox?.let { result.add(it) }
                    }
                }
            }
        onFound(result)
    }
}