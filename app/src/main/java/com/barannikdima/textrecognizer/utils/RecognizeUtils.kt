package com.barannikdima.textrecognizer.utils

import android.graphics.Bitmap
import android.graphics.Rect
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText

class RecognizeUtils {

    private val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

    private var recognizeResult: FirebaseVisionText? = null

    fun recognize(bitmap: Bitmap) {
        recognizeResult = null
        val visionImage = FirebaseVisionImage.fromBitmap(bitmap)
        detector.processImage(visionImage)
            .addOnSuccessListener(::onSuccessListener)
            .addOnFailureListener(::onFailureListener)
    }

    fun find(text: String, onFound: (List<Rect>) -> Unit) {

    }

    private fun onSuccessListener(firebaseVisionText: FirebaseVisionText) {

    }

    private fun onFailureListener(exception: Exception) {

    }

}