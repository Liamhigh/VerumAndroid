package com.verumomnis.forensic.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.verumomnis.forensic.VerumOmnisEngine

@Composable
fun ForensicScreen(nav: NavController) {
    val context = LocalContext.current
    var input by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }
    var isProcessing by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Enter your statement or evidence below:")
        Spacer(Modifier.height(10.dp))
        TextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier.fillMaxWidth().height(160.dp),
            enabled = !isProcessing
        )
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                isProcessing = true
                val result = VerumOmnisEngine.process(context, input)
                resultText = "Contradictions:\n${result.contradictions.joinToString("\n")}\n\n" +
                        "Behaviour Flags:\n${result.behaviouralFlags.joinToString("\n")}\n\n" +
                        "PDF: ${result.pdfFile.path}"
                isProcessing = false
            },
            enabled = input.isNotBlank() && !isProcessing
        ) {
            Text(if (isProcessing) "Processing..." else "Run Forensic Analysis")
        }
        Spacer(Modifier.height(20.dp))
        if (resultText.isNotEmpty()) {
            Text("Results:", style = MaterialTheme.typography.titleMedium)
            Text(resultText)
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { nav.navigate("pdf") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("View PDF Report")
            }
        }
    }
}
