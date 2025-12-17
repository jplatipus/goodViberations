package com.j3t.dataentryapp.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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

class AddEntryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataEntryAppTheme {
                AddEntryActivityContent()
            }
        }
    }
}

@Composable
fun AddEntryActivityContent() {
    var entryName by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = entryName,
                onValueChange = { entryName = it },
                label = { Text("Entry Name") }
            )
            Text("Creation Date and Time")
            Text("Last Modified Date and Time")
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") }
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )
            LazyColumn(modifier = Modifier.weight(1f)) {
                // Placeholder for list of detail fields
                items(2) { index ->
                    OutlinedTextField(value = "", onValueChange = {}, label = { Text("Detail Field $index") })
                }
            }
            Row {
                Button(onClick = { /* TODO: Implement delete field */ }) {
                    Text("Delete Field")
                }
                Button(onClick = { /* TODO: Implement add field */ }) {
                    Text("Add Field")
                }
            }
            Row {
                Button(onClick = { /* TODO: Implement save */ }) {
                    Text("Save")
                }
                Button(onClick = { /* TODO: Implement cancel */ }) {
                    Text("Cancel")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddEntryActivityContentPreview() {
    DataEntryAppTheme {
        AddEntryActivityContent()
    }
}
