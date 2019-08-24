package com.barannikdima.textrecognizer.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.barannikdima.textrecognizer.R
import kotlinx.android.synthetic.main.activity_recognize.*

class RecognizeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognize)

        init()
    }

    fun init() {
        val uri = intent.getParcelableExtra<Uri>(URI_EXTRA)
        val bmp = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        image_view.setImageBitmap(bmp)
    }

    companion object {
        private const val URI_EXTRA = "URI_EXTRA"

        fun createIntent(context: Context, uri: Uri) = Intent(context, RecognizeActivity::class.java).apply {
            putExtra(URI_EXTRA, uri)
        }
    }
}
