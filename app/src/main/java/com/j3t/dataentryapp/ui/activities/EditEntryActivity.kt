package com.j3t.dataentryapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.j3t.dataentryapp.R

class EditEntryActivity : AppCompatActivity() {

    private lateinit var txtEntryName: EditText
    private lateinit var lblDateTime: TextView
    private lateinit var lblLastModifiedDateTime: TextView
    private lateinit var mtxNotes: EditText
    private lateinit var txtPassword: EditText
    private lateinit var lstDetailFields: ListView
    private lateinit var btnDelField: Button
    private lateinit var btnAddField: Button
    private lateinit var btnSave: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_entry)

        title = "Edit Entry"

        txtEntryName = findViewById(R.id.txtEntryName)
        lblDateTime = findViewById(R.id.lblDateTime)
        lblLastModifiedDateTime = findViewById(R.id.lblLastModifiedDateTime)
        mtxNotes = findViewById(R.id.mtxNotes)
        txtPassword = findViewById(R.id.txtPassword)
        lstDetailFields = findViewById(R.id.lstDetailFields)
        btnDelField = findViewById(R.id.btnDelField)
        btnAddField = findViewById(R.id.btnAddField)
        btnSave = findViewById(R.id.btnSave)
        btnBack = findViewById(R.id.btnBack)
    }

    @Preview(showBackground = true, name = "Edit Entry Activity Preview")
    @Composable
    fun EditEntryActivityPreview() {
        // This Composable wraps the XML layout for previewing.
        AndroidView(
            factory = { context ->
                // Inflate the XML layout using the activity's context.
                android.view.View.inflate(context, R.layout.activity_edit_entry, null)
            },
            update = { view ->
                // You can add logic here to update the view in the preview if needed.
                // For example, finding a button and setting its text.
            }
        )
    }
}