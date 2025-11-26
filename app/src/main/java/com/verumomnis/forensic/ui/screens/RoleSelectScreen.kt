package com.verumomnis.forensic.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.verumomnis.forensic.ui.components.VerumSectionCard

@Composable
fun RoleSelectScreen(nav: NavController) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Who are you?", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(20.dp))
        VerumSectionCard("Private Person (Free)") { nav.navigate("forensics") }
        VerumSectionCard("Institution / Company (20% Fee After Trial)") { nav.navigate("forensics") }
    }
}
