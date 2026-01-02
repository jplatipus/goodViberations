package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.j3t.dataentryapp.R

class ImportExportStoreActivity : AppCompatActivity() {

    private lateinit var btnImportStore: Button
    private lateinit var btnExportStore: Button
    private lateinit var btnBack: Button
    private lateinit var btnHelp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_import_export_store)

        title = "Import/Export Store"

        btnImportStore = findViewById(R.id.btnImportStore)
        btnExportStore = findViewById(R.id.btnExportStore)
        btnBack = findViewById(R.id.btnBack)
        btnHelp = findViewById(R.id.btnHelp)

        btnBack.setOnClickListener {
            finish()
        }

        btnHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            intent.putExtra("assetFileName", "ImportExportStoreActivityHelp.html")
            startActivity(intent)
        }
    }

    @Preview(showBackground = true, name = "Import Export Store Activity Preview")
    @Composable
    fun ImportExportStoreActivityPreview() {
        // This Composable wraps the XML layout for previewing.
        AndroidView(
            factory = { context ->
                // Inflate the XML layout using the activity's context.
                android.view.View.inflate(context, R.layout.activity_import_export_store, null)
            },
            update = { view ->
                // You can add logic here to update the view in the preview if needed.
                // For example, finding a button and setting its text.
            }
        )
    }
}