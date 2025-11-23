package com.verumomnis.forensic.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TaxReturnScreen(nav: NavController) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Tax Returns", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(10.dp))
        Text("Prices: 50% cheaper than local options.")
        Spacer(Modifier.height(20.dp))
        Button(onClick = { /* open tax tool */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Start Tax Return")
        }
    }
}
