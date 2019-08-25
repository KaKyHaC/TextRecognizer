package com.barannikdima.textrecognizer.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.barannikdima.textrecognizer.R
import com.barannikdima.textrecognizer.utils.BitmapUtils
import com.barannikdima.textrecognizer.utils.RecognizeUtils
import kotlinx.android.synthetic.main.activity_recognize.*


class RecognizeActivity : AppCompatActivity() {

    private val recognizeUtils by lazy { RecognizeUtils() }

    private var originImage: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recognize)

        init()
    }

    private fun init() {
        search_btn.setOnClickListener { startFinding() }
        search_text.setOnEditorActionListener(::onEditorAction)
        val uri = intent.getParcelableExtra<Uri>(URI_EXTRA)
        originImage = BitmapUtils.loadRotatedBitmap(uri, this)
        image_view.setImageBitmap(originImage)
        recognizeUtils.recognize(originImage!!)
    }

    private fun onEditorAction(v: TextView, actionId: Int?, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            startFinding()
            return true
        }
        return false
    }

    private fun startFinding() {
        recognizeUtils.find(search_text.text.toString(), ::onFound)
    }

    private fun onFound(rects: List<Rect>) {
        originImage?.let { bmp -> image_view.setImageBitmap(BitmapUtils.drawRects(bmp, rects)) }
    }

    companion object {
        private const val URI_EXTRA = "URI_EXTRA"

        fun createIntent(context: Context, uri: Uri) = Intent(context, RecognizeActivity::class.java).apply {
            putExtra(URI_EXTRA, uri)
        }
    }
}
