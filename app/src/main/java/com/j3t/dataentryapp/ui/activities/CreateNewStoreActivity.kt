package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.j3t.dataentryapp.R
import com.j3t.dataentryapp.datalayer.DataEntry
import com.j3t.dataentryapp.datalayer.DataField
import com.j3t.dataentryapp.datalayer.DataLayer
import java.util.Locale

class CreateNewStoreActivity : AppCompatActivity() {

    private lateinit var txtPassword: EditText
    private lateinit var txtMemorableDate: EditText
    private lateinit var txtConfirmPassword: EditText
    private lateinit var txtConfirmMemorableDate: EditText
    private lateinit var btnCreateStore: Button
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var dataLayer: DataLayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_store)

        title = "Create Store"
        dataLayer = DataLayer(this)

        txtPassword = findViewById(R.id.txtPassword)
        txtMemorableDate = findViewById(R.id.txtMemorableDate)
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword)
        txtConfirmMemorableDate = findViewById(R.id.txtConfirmMemorableDate)
        btnCreateStore = findViewById(R.id.btnCreateStore)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnBack -> {
                    finish()
                    true
                }
                R.id.btnHelp -> {
                    val intent = Intent(this, HelpActivity::class.java)
                    intent.putExtra("assetFileName", "CreateNewStoreActivityHelp.html")
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        btnCreateStore.setOnClickListener {
            if (validateInputs()) {
                createNewStore()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val password = txtPassword.text.toString()
        val confirmPassword = txtConfirmPassword.text.toString()
        val memorableDate = txtMemorableDate.text.toString()
        val confirmMemorableDate = txtConfirmMemorableDate.text.toString()

        if (password.length < 8) {
            showError("A password must have at least 8 characters")
            return false
        }
        if (memorableDate.isEmpty()) {
            showError("Please supply a date that is memorable to you.")
            return false
        }
        if (password != confirmPassword) {
            showError("The passwords do not match, please ensure they are the same")
            return false
        }
        if (memorableDate != confirmMemorableDate) {
            showError("The memorable dates do not match, please ensure they are the same")
            return false
        }
        return true
    }

    private fun showError(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun createNewStore() {
        val field1 = DataField("A field name", "A field value")
        val field2 = DataField("A second field", "A value for the second field")
        val dataEntry1 = DataEntry(
            name = "A sample",
            notes = "These are some sample\nnotes for the sample",
            password = "apassword"
        )
        dataEntry1.detailFields.add(field1)
        dataEntry1.detailFields.add(field2)

        val map = mutableMapOf<String, DataEntry>()
        map[dataEntry1.name.lowercase(Locale.getDefault())] = dataEntry1
        dataLayer.createStore(map)

        finish()
        startActivity(Intent(this, ListEntriesActivity::class.java))
    }
}
