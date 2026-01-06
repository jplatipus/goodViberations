package com.j3t.dataentryapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.j3t.dataentryapp.R

class AboutActivity : AppCompatActivity() {

    private lateinit var htmlText: WebView
    private lateinit var btnOk: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        title = "About"

        htmlText = findViewById(R.id.htmlText)
        btnOk = findViewById(R.id.btnOk)

        htmlText.loadUrl("file:///android_asset/About.html")

        btnOk.setOnClickListener {
            finish()
        }
    }

    @Preview(showBackground = true, name = "About Activity Preview")
    @Composable
    fun AboutActivityPreview() {
        // This Composable wraps the XML layout for previewing.
        AndroidView(
            factory = { context ->
                // Inflate the XML layout using the activity's context.
                android.view.View.inflate(context, R.layout.activity_about, null)
            },
            update = { view ->
                // You can add logic here to update the view in the preview if needed.
                // For example, finding a button and setting its text.
            }
        )
    }
}