package com.j3t.dataentryapp.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.j3t.dataentryapp.ui.theme.DataEntryAppTheme

class ChangeStorePasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataEntryAppTheme {
                ChangeStorePasswordActivityContent()
            }
        }
    }
}

@Composable
fun ChangeStorePasswordActivityContent() {
    var newPassword by remember { mutableStateOf("") }
    var newMemorableDate by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }
    var confirmNewMemorableDate by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("New Password") }
            )
            OutlinedTextField(
                value = newMemorableDate,
                onValueChange = { newMemorableDate = it },
                label = { Text("New Memorable Date") }
            )
            OutlinedTextField(
                value = confirmNewPassword,
                onValueChange = { confirmNewPassword = it },
                label = { Text("Confirm New Password") }
            )
            OutlinedTextField(
                value = confirmNewMemorableDate,
                onValueChange = { confirmNewMemorableDate = it },
                label = { Text("Confirm New Memorable Date") }
            )
            Button(onClick = { /* TODO: Implement save changes */ }) {
                Text("Save Changes")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeStorePasswordActivityContentPreview() {
    DataEntryAppTheme {
        ChangeStorePasswordActivityContent()
    }
}
