package com.j3t.dataentryapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.j3t.dataentryapp.R

class ListEntriesActivity : AppCompatActivity() {

    private lateinit var vlsEntryNames: ListView
    private lateinit var btnNewEntry: Button
    private lateinit var btnSearchEntryNames: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_entries)

        title = "List Entries"

        vlsEntryNames = findViewById(R.id.vlsEntryNames)
        btnNewEntry = findViewById(R.id.btnNewEntry)
        btnSearchEntryNames = findViewById(R.id.btnSearchEntryNames)
        btnBack = findViewById(R.id.btnBack)
    }

    @Preview(showBackground = true, name = "ListEntries Activity Preview")
    @Composable
    fun ListEntriesActivityPreview() {
        // This Composable wraps the XML layout for previewing.
        AndroidView(
            factory = { context ->
                // Inflate the XML layout using the activity's context.
                android.view.View.inflate(context, R.layout.activity_list_entries, null)
            },
            update = { view ->
                // You can add logic here to update the view in the preview if needed.
                // For example, finding a button and setting its text.
            }
        )
    }
}