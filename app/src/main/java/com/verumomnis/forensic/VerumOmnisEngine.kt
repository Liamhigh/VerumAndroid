package com.verumomnis.forensic

import android.content.Context
import com.verumomnis.forensic.contradiction.*
import com.verumomnis.forensic.pdf.PdfSealEngine
import com.verumomnis.forensic.model.ForensicResult

object VerumOmnisEngine {
    private val history = mutableListOf<Claim>()

    fun process(context: Context, text: String): ForensicResult {
        val claim = ClaimExtractor.extract(text)
        val contradictions = ContradictionDetector.check(claim, history)
        val behaviourFlags = BehaviourBrain.detectStressMarkers(text)

        history.add(claim)

        val report = buildString {
            appendLine("VERUM OMNIS FORENSIC REPORT")
            appendLine("Input: $text\n")
            appendLine("Contradictions:")
            contradictions.forEach {
                appendLine(" - $it")
            }
            appendLine("\nBehaviour Flags:")
            behaviourFlags.forEach {
                appendLine(" - $it")
            }
        }

        val pdf = PdfSealEngine.createSealedPdf(context, report)

        return ForensicResult(
            contradictions = contradictions,
            behaviouralFlags = behaviourFlags,
            pdfFile = pdf
        )
    }
}
