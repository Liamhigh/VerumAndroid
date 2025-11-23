package com.verumomnis.forensic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.verumomnis.forensic.ui.VoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VoTheme {
                var inputText by remember { mutableStateOf("") }
                var result by remember { mutableStateOf<String?>(null) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        "VERUM OMNIS – FORENSIC ENGINE",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(16.dp))

                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(8.dp)
                            .border(1.dp, MaterialTheme.colorScheme.primary),
                        value = inputText,
                        onValueChange = { inputText = it }
                    )

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = {
                        if (inputText.isNotBlank()) {
                            val forensicResult = VerumOmnisEngine.process(this@MainActivity, inputText)
                            result = "Contradictions: ${forensicResult.contradictions}\n" +
                                    "Behaviour: ${forensicResult.behaviouralFlags}\n" +
                                    "PDF Saved: ${forensicResult.pdfFile.path}"
                        }
                    }) {
                        Text("Run Analysis")
                    }

                    Spacer(Modifier.height(16.dp))

                    result?.let {
                        Text("Results:", style = MaterialTheme.typography.titleMedium)
                        Text(it)
                    }
                }
            }
        }
    }
}
