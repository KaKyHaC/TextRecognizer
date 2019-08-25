package com.barannikdima.textrecognizer.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barannikdima.textrecognizer.R
import com.barannikdima.textrecognizer.utils.PaintUtils
import com.barannikdima.textrecognizer.utils.RecognizeUtils
import kotlinx.android.synthetic.main.activity_recognize.*

class RecognizeActivity : AppCompatActivity() {

    private val recognizeUtils by lazy { RecognizeUtils() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognize)

        init()
    }

    private fun init() {
        search_btn.setOnClickListener {
            recognizeUtils.find(search_text.text.toString()) {
                log(it.toString())
            }
        }
        val uri = intent.getParcelableExtra<Uri>(URI_EXTRA)
        var bmp = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        bmp = PaintUtils.printRect(bmp, Rect(10,10,200,100))
        image_view.setImageBitmap(bmp)
        recognizeUtils.recognize(bmp)
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
