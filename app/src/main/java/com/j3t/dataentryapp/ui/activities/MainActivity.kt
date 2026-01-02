package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.j3t.dataentryapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnOpenStore: Button
    private lateinit var btnCreateNewStore: Button
    private lateinit var btnChangeStorePassword: Button
    private lateinit var btnClearExistingStore: Button
    private lateinit var btnImportExportStore: Button
    private lateinit var btnAbout: Button
    private lateinit var btnHelp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Options"

        btnOpenStore = findViewById(R.id.btnOpenStore)
        btnCreateNewStore = findViewById(R.id.btnCreateNewStore)
        btnChangeStorePassword = findViewById(R.id.btnChangeStorePassword)
        btnClearExistingStore = findViewById(R.id.btnClearExistingStore)
        btnImportExportStore = findViewById(R.id.btnImportExportStore)
        btnAbout = findViewById(R.id.btnAbout)
        btnHelp = findViewById(R.id.btnHelp)

        btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        btnHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            intent.putExtra("assetFileName", "MainActivityHelp.html")
            startActivity(intent)
        }
    }

    @Preview(showBackground = true, name = "Main Activity Preview")
    @Composable
    fun MainActivityPreview() {
        // This Composable wraps the XML layout for previewing.
        AndroidView(
            factory = { context ->
                // Inflate the XML layout using the activity's context.
                android.view.View.inflate(context, R.layout.activity_main, null)
            },
            update = { view ->
                // You can add logic here to update the view in the preview if needed.
                // For example, finding a button and setting its text.
            }
        )
    }
}