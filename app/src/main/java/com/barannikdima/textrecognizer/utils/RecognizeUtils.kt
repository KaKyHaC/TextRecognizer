package com.barannikdima.textrecognizer.utils

import android.graphics.Bitmap
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText

class RecognizeUtils {

    private val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

    private var recognizeResult: Task<FirebaseVisionText>? = null

    fun recognize(bitmap: Bitmap) {
        recognizeResult = null
        val visionImage = FirebaseVisionImage.fromBitmap(bitmap)
        recognizeResult = detector.processImage(visionImage)
            .addOnFailureListener { Log.d("RecognizeUtils", it.toString()) }
    }

    fun find(text: String, onFound: RectListCallback) {
        if (recognizeResult?.isSuccessful.isTrue()) {
            recognizeResult?.result?.let { find(it, text, onFound) }
        } else {
            recognizeResult?.addOnSuccessListener { find(it, text, onFound) }
        }
    }

    private fun find(
        firebaseVisionText: FirebaseVisionText,
        text: String,
        onFound: RectListCallback
    ) {

    }
}