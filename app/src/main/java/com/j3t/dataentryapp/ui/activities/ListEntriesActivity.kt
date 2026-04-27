package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.j3t.dataentryapp.R
import com.j3t.dataentryapp.datalayer.DataLayer

class ListEntriesActivity : AppCompatActivity() {

    private lateinit var vlsEntryNames: ListView
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var dataLayer: DataLayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_entries)

        title = "List Entries"
        dataLayer = DataLayer(this)

        vlsEntryNames = findViewById(R.id.vlsEntryNames)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        populateEntryList()

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnBack -> {
                    finish()
                    true
                }
                R.id.btnAddEntry -> {
                    startActivity(Intent(this, AddEntryActivity::class.java))
                    true
                }
                R.id.btnSearch -> {
                    startActivity(Intent(this, SearchEntriesActivity::class.java))
                    true
                }
                R.id.btnHelp -> {
                    val intent = Intent(this, HelpActivity::class.java)
                    intent.putExtra("assetFileName", "listEntries.html")
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        vlsEntryNames.setOnItemClickListener { _, _, position, _ ->
            val entryName = vlsEntryNames.adapter.getItem(position) as String
            val intent = Intent(this, ViewEntryActivity::class.java)
            intent.putExtra("entryName", entryName)
            finish()
            startActivity(intent)
        }
    }

    private fun populateEntryList() {
        val entryNames = dataLayer.listEntryNames()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, entryNames)
        vlsEntryNames.adapter = adapter
    }
}
