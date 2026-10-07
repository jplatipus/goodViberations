package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.j3t.dataentryapp.R
import com.j3t.dataentryapp.datalayer.DataEntry
import com.j3t.dataentryapp.datalayer.DataLayer
import java.text.SimpleDateFormat
import java.util.Locale

class EditEntryActivity : AppCompatActivity() {

    private lateinit var txtEntryName: EditText
    private lateinit var lblDateTime: TextView
    private lateinit var lblLastModifiedDateTime: TextView
    private lateinit var mtxNotes: EditText
    private lateinit var txtPassword: EditText
    private lateinit var lstDetailFields: ListView
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var dataLayer: DataLayer

    private var entryName: String = ""
    private var selectedListIndex: Int = -1
    private lateinit var entryData: DataEntry
    private val rows = mutableListOf<EditEntryRow>()
    private lateinit var adapter: EditEntryRowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_entry)

        dataLayer = DataLayer(this)
        entryName = intent.getStringExtra("entryName") ?: ""
        title = if (entryName.isNotEmpty()) entryName else "Edit Entry"

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
            startActivity(Intent(this, ListEntriesActivity::class.java))
        }

        txtEntryName = findViewById(R.id.txtEntryName)
        lblDateTime = findViewById(R.id.lblDateTime)
        lblLastModifiedDateTime = findViewById(R.id.lblLastModifiedDateTime)
        mtxNotes = findViewById(R.id.mtxNotes)
        txtPassword = findViewById(R.id.txtPassword)
        lstDetailFields = findViewById(R.id.lstDetailFields)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        initDataAndUi()

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnExit -> {
                    finish()
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.btnAddField -> {
                    selectedListIndex = -1
                    val intent = Intent(this, EditEntryFieldActivity::class.java)
                    intent.putExtra("entryName", entryData.name.ifEmpty { entryName })
                    intent.putExtra("selectedListIndex", selectedListIndex)
                    startActivity(intent)
                    true
                }
                R.id.btnSave -> {
                    saveEntryAndNavigate()
                    true
                }
                R.id.btnHelp -> {
                    val intent = Intent(this, HelpActivity::class.java)
                    intent.putExtra("assetFileName", "entryEdit.html")
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        lstDetailFields.setOnItemClickListener { _, _, position, _ ->
            selectedListIndex = position
            val view = lstDetailFields.getChildAt(position - lstDetailFields.firstVisiblePosition)
            view?.requestFocus()
        }
    }

    private fun initDataAndUi() {
        selectedListIndex = -1
        if (entryName.isBlank()) {
            entryData = DataEntry(name = "")
        } else {
            entryData = dataLayer.loadEntry(entryName)
        }

        txtEntryName.setText(entryData.name)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        lblDateTime.text = "Creation: " + dateFormat.format(entryData.creationDateTime)
        lblLastModifiedDateTime.text = "Modified: " + dateFormat.format(entryData.lastModifiedDateTime)
        mtxNotes.setText(entryData.notes)
        txtPassword.setText(entryData.password)

        rows.clear()
        rows.add(EditEntryRow("Name", entryData.name))
        rows.add(EditEntryRow("Created", dateFormat.format(entryData.creationDateTime)))
        rows.add(EditEntryRow("Modified", dateFormat.format(entryData.lastModifiedDateTime)))
        rows.add(EditEntryRow("Password", entryData.password))

        entryData.detailFields.forEachIndexed { index, field ->
            rows.add(EditEntryRow(field.name, field.value, detailFieldIndex = index))
        }

        rows.add(EditEntryRow("Notes", entryData.notes))

        adapter = EditEntryRowAdapter(this, rows) { position, _ ->
            selectedListIndex = position
            val intent = Intent(this, EditEntryFieldActivity::class.java)
            intent.putExtra("entryName", entryData.name.ifEmpty { entryName })
            intent.putExtra("selectedListIndex", selectedListIndex)
            startActivity(intent)
        }
        lstDetailFields.adapter = adapter
    }

    private fun saveEntryAndNavigate() {
        val updatedName = txtEntryName.text.toString().trim()
        if (updatedName.isNotEmpty()) {
            entryData.name = updatedName
        }
        entryData.notes = mtxNotes.text.toString()
        entryData.password = txtPassword.text.toString()

        dataLayer.saveEntry(entryData)

        finish()
        val intent = Intent(this, ViewEntryActivity::class.java)
        intent.putExtra("entryName", entryData.name)
        startActivity(intent)
    }
}
