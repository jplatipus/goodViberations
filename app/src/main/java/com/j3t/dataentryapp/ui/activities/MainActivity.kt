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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.j3t.dataentryapp.ui.theme.DataEntryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataEntryAppTheme {
                MainActivityContent()
            }
        }
    }
}

@Composable
fun MainActivityContent() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { /* TODO: Navigate to OpenStoreActivity */ }) {
                Text("Open Store")
            }
            Button(onClick = { /* TODO: Navigate to CreateNewStoreActivity */ }) {
                Text("Create New Store")
            }
            Button(onClick = { /* TODO: Implement clear existing store */ }) {
                Text("Clear Existing Store")
            }
            Button(onClick = { /* TODO: Navigate to ImportExportStoreActivity */ }) {
                Text("Import/Export Store")
            }
            Button(onClick = { /* TODO: Navigate to AboutActivity */ }) {
                Text("About")
            }
            Button(onClick = { /* TODO: Navigate to HelpActivity */ }) {
                Text("Help")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityContentPreview() {
    DataEntryAppTheme {
        MainActivityContent()
    }
}
