package com.verum.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.verum.android.ui.VoTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private var selectedText by mutableStateOf("")

    private val pickTextFile =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                val text = FileUtils.readTextFromUri(this, uri)
                selectedText = text
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VoTheme {
                var result by remember { mutableStateOf<String?>(null) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        "Verum Omnis – Contradiction Engine",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = { pickTextFile.launch("*/*") }) {
                        Text("Upload File")
                    }

                    Spacer(Modifier.height(16.dp))

                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(8.dp)
                            .border(1.dp, MaterialTheme.colorScheme.primary),
                        value = selectedText,
                        onValueChange = { selectedText = it }
                    )

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = {
                        if (selectedText.isBlank()) {
                            Toast.makeText(this@MainActivity, "No text.", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        val statements = StatementParser.parse(selectedText)
                        val report = ContradictionEngine.analyze(statements)

                        val output = File(filesDir, "verum_report.pdf")
                        PdfReportGenerator.generateReport(
                            this@MainActivity,
                            report,
                            R.drawable.verum_logo,
                            output
                        )

                        result = output.absolutePath
                    }) {
                        Text("Run Engine + Generate PDF")
                    }

                    Spacer(Modifier.height(16.dp))

                    result?.let {
                        Text("Report saved:", style = MaterialTheme.typography.titleMedium)
                        Text(it)
                    }
                }
            }
        }
    }
}
