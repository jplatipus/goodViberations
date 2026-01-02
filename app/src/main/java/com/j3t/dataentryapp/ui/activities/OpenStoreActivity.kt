package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.j3t.dataentryapp.R

class OpenStoreActivity : AppCompatActivity() {

    private lateinit var txtPassword: EditText
    private lateinit var txtMemorableDate: EditText
    private lateinit var btnOpenStore: Button
    private lateinit var btnBack: Button
    private lateinit var btnHelp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_store)

        title = "Open Store"

        txtPassword = findViewById(R.id.txtPassword)
        txtMemorableDate = findViewById(R.id.txtMemorableDate)
        btnOpenStore = findViewById(R.id.btnOpenStore)
        btnBack = findViewById(R.id.btnBack)
        btnHelp = findViewById(R.id.btnHelp)

        btnBack.setOnClickListener {
            finish()
        }

        btnHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            intent.putExtra("assetFileName", "OpenStoreActivityHelp.html")
            startActivity(intent)
        }
    }

    @Preview(showBackground = true, name = "Open Store Activity Preview")
    @Composable
    fun OpenStoreActivityActivityPreview() {
        // This Composable wraps the XML layout for previewing.
        AndroidView(
            factory = { context ->
                // Inflate the XML layout using the activity's context.
                android.view.View.inflate(context, R.layout.activity_open_store, null)
            },
            update = { view ->
                // You can add logic here to update the view in the preview if needed.
                // For example, finding a button and setting its text.
            }
        )
    }
}