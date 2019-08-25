package com.barannikdima.textrecognizer.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barannikdima.textrecognizer.R
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_recognize.*

class RecognizeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognize)

        init()
    }

    private fun init() {
        val uri = intent.getParcelableExtra<Uri>(URI_EXTRA)
        val bmp = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        image_view.setImageBitmap(bmp)

        val visionImage = FirebaseVisionImage.fromBitmap(bmp)

        val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

        val result = detector.processImage(visionImage)
                .addOnSuccessListener { firebaseVisionText ->
                    log("addOnSuccessListener  $firebaseVisionText" )
                }
                .addOnFailureListener {
                    log("fail $it")
                    // Task failed with an exception
                    // ...
                }

    }

    private fun log(message: String) {
        Log.d("RecognizeActivity", message)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val URI_EXTRA = "URI_EXTRA"

        fun createIntent(context: Context, uri: Uri) = Intent(context, RecognizeActivity::class.java).apply {
            putExtra(URI_EXTRA, uri)
        }
    }
}
