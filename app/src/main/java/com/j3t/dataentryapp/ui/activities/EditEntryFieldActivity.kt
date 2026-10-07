package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.j3t.dataentryapp.R
import com.j3t.dataentryapp.datalayer.DataEntry
import com.j3t.dataentryapp.datalayer.DataField
import com.j3t.dataentryapp.datalayer.DataLayer

class EditEntryFieldActivity : AppCompatActivity() {

    private lateinit var txtFieldName: EditText
    private lateinit var txtFieldValue: EditText
    private lateinit var btnOK: Button
    private lateinit var btnBackAction: Button
    private lateinit var btnHelpAction: Button
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var dataLayer: DataLayer

    private var entryName: String = ""
    private var selectedListIndex: Int = -1
    private lateinit var entryData: DataEntry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_entry_field)

        dataLayer = DataLayer(this)
        entryName = intent.getStringExtra("entryName") ?: ""
        selectedListIndex = intent.getIntExtra("selectedListIndex", -1)

        title = if (entryName.isNotEmpty()) entryName else "Edit Field"

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            navigateBackToEditEntry()
        }

        txtFieldName = findViewById(R.id.txtFieldName)
        txtFieldValue = findViewById(R.id.txtFieldValue)
        btnOK = findViewById(R.id.btnOK)
        btnBackAction = findViewById(R.id.btnBackAction)
        btnHelpAction = findViewById(R.id.btnHelpAction)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        initFieldData()

        btnOK.setOnClickListener {
            saveFieldAndNavigate()
        }

        btnBackAction.setOnClickListener {
            navigateBackToEditEntry()
        }

        btnHelpAction.setOnClickListener {
            openHelp()
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnExit -> {
                    finish()
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.btnAddField -> {
                    selectedListIndex = -1
                    txtFieldName.setText("")
                    txtFieldValue.setText("")
                    txtFieldName.requestFocus()
                    true
                }
                R.id.btnSave -> {
                    saveFieldAndNavigate()
                    true
                }
                R.id.btnHelp -> {
                    openHelp()
                    true
                }
                else -> false
            }
        }
    }

    private fun initFieldData() {
        if (entryName.isBlank()) {
            selectedListIndex = -1
            entryData = DataEntry(name = "")
            txtFieldName.setText("")
            txtFieldValue.setText("")
        } else {
            entryData = dataLayer.loadEntry(entryName)
            if (selectedListIndex == 0) {
                txtFieldName.setText("Name")
                txtFieldValue.setText(entryData.name)
            } else if (selectedListIndex == 3) {
                txtFieldName.setText("Password")
                txtFieldValue.setText(entryData.password)
            } else if (selectedListIndex >= 4 && selectedListIndex < 4 + entryData.detailFields.size) {
                val detailIndex = selectedListIndex - 4
                val field = entryData.detailFields[detailIndex]
                txtFieldName.setText(field.name)
                txtFieldValue.setText(field.value)
            } else if (selectedListIndex == 4 + entryData.detailFields.size) {
                txtFieldName.setText("Notes")
                txtFieldValue.setText(entryData.notes)
            } else {
                txtFieldName.setText("")
                txtFieldValue.setText("")
            }
        }
    }

    private fun saveFieldAndNavigate() {
        val fName = txtFieldName.text.toString().trim()
        val fValue = txtFieldValue.text.toString()

        if (selectedListIndex == 0) {
            if (fValue.isNotEmpty()) {
                entryData.name = fValue
            }
        } else if (selectedListIndex == 3) {
            entryData.password = fValue
        } else if (selectedListIndex == 4 + entryData.detailFields.size) {
            entryData.notes = fValue
        } else if (selectedListIndex >= 4 && selectedListIndex < 4 + entryData.detailFields.size) {
            val detailIndex = selectedListIndex - 4
            entryData.detailFields[detailIndex] = DataField(if (fName.isNotEmpty()) fName else "Field", fValue)
        } else {
            if (fName.isNotEmpty()) {
                entryData.detailFields.add(DataField(fName, fValue))
            }
        }

        dataLayer.saveEntry(entryData)

        finish()
        val intent = Intent(this, EditEntryActivity::class.java)
        intent.putExtra("entryName", entryData.name)
        startActivity(intent)
    }

    private fun navigateBackToEditEntry() {
        finish()
        val intent = Intent(this, EditEntryActivity::class.java)
        intent.putExtra("entryName", entryData.name)
        startActivity(intent)
    }

    private fun openHelp() {
        val intent = Intent(this, HelpActivity::class.java)
        intent.putExtra("assetFileName", "EditEntryField.html")
        startActivity(intent)
    }
}
