package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.j3t.dataentryapp.R
import com.j3t.dataentryapp.datalayer.DataLayer

class ClearExistingStoreActivity : AppCompatActivity() {

    private lateinit var btnConfirmClear: Button
    private lateinit var btnBack: Button
    private lateinit var dataLayer: DataLayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clear_existing_store)

        title = "Clear Existing Store"
        dataLayer = DataLayer(this)

        btnConfirmClear = findViewById(R.id.btnConfirmClear)
        btnBack = findViewById(R.id.btnBack)

        btnConfirmClear.setOnClickListener {
            dataLayer.deleteStore()
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnBack.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
