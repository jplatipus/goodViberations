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

class CreateNewStoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataEntryAppTheme {
                CreateNewStoreActivityContent()
            }
        }
    }
}

@Composable
fun CreateNewStoreActivityContent() {
    var password by remember { mutableStateOf("") }
    var memorableDate by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmMemorableDate by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )
            OutlinedTextField(
                value = memorableDate,
                onValueChange = { memorableDate = it },
                label = { Text("Memorable Date") }
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") }
            )
            OutlinedTextField(
                value = confirmMemorableDate,
                onValueChange = { confirmMemorableDate = it },
                label = { Text("Confirm Memorable Date") }
            )
            Button(onClick = { /* TODO: Implement create store */ }) {
                Text("Create Store")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateNewStoreActivityContentPreview() {
    DataEntryAppTheme {
        CreateNewStoreActivityContent()
    }
}
