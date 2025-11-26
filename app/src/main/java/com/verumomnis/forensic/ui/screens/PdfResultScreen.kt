package com.verumomnis.forensic.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PdfResultScreen(nav: NavController) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Your sealed forensic PDF is ready.", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(20.dp))
        Button(onClick = { /* share */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Share / Export PDF")
        }
        Spacer(Modifier.height(10.dp))
        Button(onClick = { nav.navigate("landing") }, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Home")
        }
    }
}
