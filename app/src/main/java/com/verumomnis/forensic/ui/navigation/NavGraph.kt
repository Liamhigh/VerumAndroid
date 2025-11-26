package com.verumomnis.forensic.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.verumomnis.forensic.ui.screens.*

@Composable
fun VerumNavGraph(nav: NavHostController) {
    NavHost(navController = nav, startDestination = "landing") {
        composable("landing") { LandingScreen(nav) }
        composable("role") { RoleSelectScreen(nav) }
        composable("forensics") { ForensicScreen(nav) }
        composable("pdf") { PdfResultScreen(nav) }
        composable("legal") { LegalAdviceScreen(nav) }
        composable("tax") { TaxReturnScreen(nav) }
    }
}
