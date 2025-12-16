package com.j3t.dataentryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.j3t.dataentryapp.ui.theme.DataEntryAppTheme

class ViewEntryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataEntryAppTheme {
                ViewEntryActivityContent()
            }
        }
    }
}

@Composable
fun ViewEntryActivityContent() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Entry Name")
            Text("Creation Date and Time")
            Text("Last Modified Date and Time")
            Text("Notes...")
            Text("Password")
            LazyColumn(modifier = Modifier.weight(1f)) {
                // Placeholder for list of detail fields
                items(5) { index ->
                    Text("Detail Field $index")
                }
            }
            Button(onClick = { /* TODO: Navigate to DeleteEntryActivity */ }) {
                Text("Delete")
            }
            Button(onClick = { /* TODO: Navigate to EditEntryActivity */ }) {
                Text("Edit")
            }
            Button(onClick = { /* TODO: Exit activity */ }) {
                Text("Exit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewEntryActivityContentPreview() {
    DataEntryAppTheme {
        ViewEntryActivityContent()
    }
}
