package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.j3t.dataentryapp.R
import com.j3t.dataentryapp.datalayer.DataLayer

class ListEntriesActivity : AppCompatActivity() {

    private lateinit var vlsEntryNames: ListView
    private lateinit var btnNewEntry: Button
    private lateinit var btnSearchEntryNames: Button
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var dataLayer: DataLayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_entries)

        title = "List Entries"
        dataLayer = DataLayer(this)

        vlsEntryNames = findViewById(R.id.vlsEntryNames)
        btnNewEntry = findViewById(R.id.btnNewEntry)
        btnSearchEntryNames = findViewById(R.id.btnSearchEntryNames)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        populateEntryList()

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnBack -> {
                    finish()
                    true
                }
                R.id.btnHelp -> {
                    val intent = Intent(this, HelpActivity::class.java)
                    // Spec says CreateNewStoreActivityHelp.html for this activity as well
                    intent.putExtra("assetFileName", "CreateNewStoreActivityHelp.html")
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        btnNewEntry.setOnClickListener {
            startActivity(Intent(this, AddEntryActivity::class.java))
        }

        btnSearchEntryNames.setOnClickListener {
            startActivity(Intent(this, SearchEntriesActivity::class.java))
        }
    }

    private fun populateEntryList() {
        val entryNames = dataLayer.listEntryNames()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, entryNames)
        vlsEntryNames.adapter = adapter
    }
}
