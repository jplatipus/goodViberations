package com.j3t.dataentryapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.j3t.dataentryapp.R

class CreateNewStoreActivity : AppCompatActivity() {

    private lateinit var txtPassword: EditText
    private lateinit var txtMemorableDate: EditText
    private lateinit var txtConfirmPassword: EditText
    private lateinit var txtConfirmMemorableDate: EditText
    private lateinit var btnCreateStore: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_store)

        title = "Create Store"

        txtPassword = findViewById(R.id.txtPassword)
        txtMemorableDate = findViewById(R.id.txtMemorableDate)
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword)
        txtConfirmMemorableDate = findViewById(R.id.txtConfirmMemorableDate)
        btnCreateStore = findViewById(R.id.btnCreateStore)
        btnBack = findViewById(R.id.btnBack)
    }

    @Preview(showBackground = true, name = "Create New Store Activity Preview")
    @Composable
    fun CreateNewStoreActivityPreview() {
        // This Composable wraps the XML layout for previewing.
        AndroidView(
            factory = { context ->
                // Inflate the XML layout using the activity's context.
                android.view.View.inflate(context, R.layout.activity_create_new_store, null)
            },
            update = { view ->
                // You can add logic here to update the view in the preview if needed.
                // For example, finding a button and setting its text.
            }
        )
    }
}