package com.j3t.dataentryapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.j3t.dataentryapp.R

class ChangeStorePasswordActivity : AppCompatActivity() {

    private lateinit var txtNewPassword: EditText
    private lateinit var txtNewMemorableDate: EditText
    private lateinit var txtConfirmNewPassword: EditText
    private lateinit var txtConfirmNewMemorableDate: EditText
    private lateinit var btnSaveChanges: Button
    private lateinit var btnBack: Button
    private lateinit var btnHelp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_store_password)

        title = "Change Store Password"

        txtNewPassword = findViewById(R.id.txtNewPassword)
        txtNewMemorableDate = findViewById(R.id.txtNewMemorableDate)
        txtConfirmNewPassword = findViewById(R.id.txtConfirmNewPassword)
        txtConfirmNewMemorableDate = findViewById(R.id.txtConfirmNewMemorableDate)
        btnSaveChanges = findViewById(R.id.btnSaveChanges)
        btnBack = findViewById(R.id.btnBack)
        btnHelp = findViewById(R.id.btnHelp)

        btnBack.setOnClickListener {
            finish()
        }

        btnHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            intent.putExtra("assetFileName", "ChangeStorePasswordActivityHelp.html")
            startActivity(intent)
        }
    }

    @Preview(showBackground = true, name = "Change Store Password Activity Preview")
    @Composable
    fun ChangeStorePasswordActivityPreview() {
        // This Composable wraps the XML layout for previewing.
        AndroidView(
            factory = { context ->
                // Inflate the XML layout using the activity's context.
                android.view.View.inflate(context, R.layout.activity_change_store_password, null)
            },
            update = { view ->
                // You can add logic here to update the view in the preview if needed.
                // For example, finding a button and setting its text.
            }
        )
    }
}