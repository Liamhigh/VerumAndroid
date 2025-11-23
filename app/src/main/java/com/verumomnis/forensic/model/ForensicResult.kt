package com.verumomnis.forensic.model

import java.io.File

data class ForensicResult(
    val contradictions: List<String>,
    val behaviouralFlags: List<String>,
    val pdfFile: File
)
