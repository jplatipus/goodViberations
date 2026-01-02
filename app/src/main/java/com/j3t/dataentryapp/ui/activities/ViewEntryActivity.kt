package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.j3t.dataentryapp.R

class ViewEntryActivity : AppCompatActivity() {

    private lateinit var lblEntryName: TextView
    private lateinit var lblDateTime: TextView
    private lateinit var lblLastModifiedDateTime: TextView
    private lateinit var mblNotes: TextView
    private lateinit var lblPassword: TextView
    private lateinit var vlsFields: ListView
    private lateinit var btnDelete: Button
    private lateinit var btnEdit: Button
    private lateinit var btnBack: Button
    private lateinit var btnHelp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_entry)

        title = "View Entry"

        lblEntryName = findViewById(R.id.lblEntryName)
        lblDateTime = findViewById(R.id.lblDateTime)
        lblLastModifiedDateTime = findViewById(R.id.lblLastModifiedDateTime)
        mblNotes = findViewById(R.id.mblNotes)
        lblPassword = findViewById(R.id.lblPassword)
        vlsFields = findViewById(R.id.vlsFields)
        btnDelete = findViewById(R.id.btnDelete)
        btnEdit = findViewById(R.id.btnEdit)
        btnBack = findViewById(R.id.btnBack)
        btnHelp = findViewById(R.id.btnHelp)

        btnBack.setOnClickListener {
            finish()
        }

        btnHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            intent.putExtra("assetFileName", "ViewEntryActivityHelp.html")
            startActivity(intent)
        }
    }

    @Preview(showBackground = true, name = "View Entry Activity Preview")
    @Composable
    fun ViewEntryActivityPreview() {
        // This Composable wraps the XML layout for previewing.
        AndroidView(
            factory = { context ->
                // Inflate the XML layout using the activity's context.
                android.view.View.inflate(context, R.layout.activity_view_entry, null)
            },
            update = { view ->
                // You can add logic here to update the view in the preview if needed.
                // For example, finding a button and setting its text.
            }
        )
    }
}