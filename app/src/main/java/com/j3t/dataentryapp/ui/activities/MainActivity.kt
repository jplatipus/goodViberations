package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.j3t.dataentryapp.R
import com.j3t.dataentryapp.datalayer.DataLayer

class MainActivity : AppCompatActivity() {

    private lateinit var btnOpenStore: Button
    private lateinit var btnCreateNewStore: Button
    private lateinit var btnChangeStorePassword: Button
    private lateinit var btnClearExistingStore: Button
    private lateinit var btnImportExportStore: Button
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var dataLayer: DataLayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "EncryptMe"
        dataLayer = DataLayer(this)

        btnOpenStore = findViewById(R.id.btnOpenStore)
        btnCreateNewStore = findViewById(R.id.btnCreateNewStore)
        btnChangeStorePassword = findViewById(R.id.btnChangeStorePassword)
        btnClearExistingStore = findViewById(R.id.btnClearExistingStore)
        btnImportExportStore = findViewById(R.id.btnImportExportStore)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnAbout -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    true
                }
                R.id.btnHelp -> {
                    val intent = Intent(this, HelpActivity::class.java)
                    intent.putExtra("assetFileName", "MainActivityHelp.html")
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        btnOpenStore.setOnClickListener {
            startActivity(Intent(this, OpenStoreActivity::class.java))
        }

        btnCreateNewStore.setOnClickListener {
            startActivity(Intent(this, CreateNewStoreActivity::class.java))
        }

        btnChangeStorePassword.setOnClickListener {
            startActivity(Intent(this, ChangeStorePasswordActivity::class.java))
        }

        btnClearExistingStore.setOnClickListener {
            // Logic for ClearExistingStoreActivity navigation (once implemented)
        }

        btnImportExportStore.setOnClickListener {
            startActivity(Intent(this, ImportExportStoreActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        updateButtonStates()
    }

    private fun updateButtonStates() {
        val storeExists = dataLayer.storeExists()
        btnOpenStore.isEnabled = storeExists
        btnChangeStorePassword.isEnabled = storeExists
        btnClearExistingStore.isEnabled = storeExists
        btnImportExportStore.isEnabled = storeExists
        btnCreateNewStore.isEnabled = !storeExists
    }
}
