package com.j3t.dataentryapp.ui.activities

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

class ListEntriesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataEntryAppTheme {
                ListEntriesActivityContent()
            }
        }
    }
}

@Composable
fun ListEntriesActivityContent() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                // Placeholder for list of entries
                items(10) { index ->
                    Text("Entry $index")
                }
            }
            Button(onClick = { /* TODO: Navigate to AddEntryActivity */ }) {
                Text("New Entry")
            }
            Button(onClick = { /* TODO: Exit activity */ }) {
                Text("Exit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListEntriesActivityContentPreview() {
    DataEntryAppTheme {
        ListEntriesActivityContent()
    }
}
