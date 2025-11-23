package com.verumomnis.forensic.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LegalAdviceScreen(nav: NavController) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Legal Advice", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(10.dp))
        Text("This is not legal advice. This is AI-generated assistance to explain your rights.")
        Spacer(Modifier.height(20.dp))
        Text("Choose your topic:")
        Spacer(Modifier.height(20.dp))
        Button(onClick = { /* open module */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Your Rights in Police Interaction")
        }
        Spacer(Modifier.height(10.dp))
        Button(onClick = { /* open module */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Small Claims Court")
        }
        Spacer(Modifier.height(10.dp))
        Button(onClick = { /* open module */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Protection Order")
        }
    }
}
