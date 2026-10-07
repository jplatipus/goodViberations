package com.j3t.dataentryapp.ui.activities

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.j3t.dataentryapp.R
import com.j3t.dataentryapp.datalayer.DataLayer
import java.text.SimpleDateFormat
import java.util.Locale

class ViewEntryActivity : AppCompatActivity() {

    private lateinit var vlsFields: ListView
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var dataLayer: DataLayer
    private var entryName: String? = null
    private var clipboardListIndex: Int = -1
    private var selectedPosition: Int = -1
    private val rows = mutableListOf<ViewEntryRow>()
    private lateinit var adapter: ViewEntryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_entry)

        dataLayer = DataLayer(this)
        entryName = intent.getStringExtra("entryName")
        title = entryName ?: "View Entry"

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
            startActivity(Intent(this, ListEntriesActivity::class.java))
        }

        vlsFields = findViewById(R.id.vlsFields)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        setupActivity()

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnExit -> {
                    finish()
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.btnEdit -> {
                    val intent = Intent(this, EditEntryActivity::class.java)
                    intent.putExtra("entryName", entryName)
                    startActivity(intent)
                    true
                }
                R.id.btnCopy -> {
                    copySelectedToClipboard()
                    true
                }
                R.id.btnHelp -> {
                    val intent = Intent(this, HelpActivity::class.java)
                    intent.putExtra("assetFileName", "entryDetails.html")
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        vlsFields.setOnItemClickListener { _, _, position, _ ->
            selectedPosition = position
            bottomNavigation.menu.findItem(R.id.btnCopy).isEnabled = true
        }
    }

    private fun setupActivity() {
        if (entryName == null) return

        val entryData = dataLayer.loadEntry(entryName!!)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())

        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = clipboard.primaryClip
        val currentClipboardText = if (clipData != null && clipData.itemCount > 0) {
            clipData.getItemAt(0).text?.toString()
        } else null

        rows.clear()
        rows.add(ViewEntryRow("Name", entryData.name))
        rows.add(ViewEntryRow("Created", dateFormat.format(entryData.creationDateTime)))
        rows.add(ViewEntryRow("Modified", dateFormat.format(entryData.lastModifiedDateTime)))
        rows.add(ViewEntryRow("Password", entryData.password))

        entryData.detailFields.forEach { field ->
            rows.add(ViewEntryRow(field.name, field.value))
        }

        rows.add(ViewEntryRow("Notes", entryData.notes))

        clipboardListIndex = -1
        for (i in rows.indices) {
            if (currentClipboardText != null && rows[i].value == currentClipboardText) {
                rows[i].isCopied = true
                clipboardListIndex = i
                break
            }
        }

        adapter = ViewEntryAdapter(this, rows)
        vlsFields.adapter = adapter
        bottomNavigation.menu.findItem(R.id.btnCopy).isEnabled = false
    }

    private fun copySelectedToClipboard() {
        if (selectedPosition < 0 || selectedPosition >= rows.size) return

        val row = rows[selectedPosition]
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", row.value)
        clipboard.setPrimaryClip(clip)

        if (clipboardListIndex != -1 && clipboardListIndex < rows.size) {
            rows[clipboardListIndex].isCopied = false
        }
        clipboardListIndex = selectedPosition
        row.isCopied = true
        adapter.notifyDataSetChanged()
    }
}
