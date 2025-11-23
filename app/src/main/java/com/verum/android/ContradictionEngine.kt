package com.verum.android

data class AnswerBlock(
    val who: String,
    val what: String,
    val whenText: String,
    val where: String,
    val how: String,
    val why: String
)

data class Contradiction(
    val type: String,
    val description: String,
    val related: List<Int>,
    val severity: String
)

data class ContradictionReport(
    val answers: AnswerBlock,
    val contradictions: List<Contradiction>,
    val summary: String
)

object ContradictionEngine {

    fun analyze(statements: List<Statement>): ContradictionReport {

        val fullText = statements.joinToString("\n") { it.text }

        val answers = AnswerBlock(
            who = extract(fullText, "who"),
            what = extract(fullText, "what"),
            whenText = extract(fullText, "when"),
            where = extract(fullText, "where"),
            how = extract(fullText, "how"),
            why = extract(fullText, "why")
        )

        val contradictions = detectContradictions(statements)

        return ContradictionReport(
            answers = answers,
            contradictions = contradictions,
            summary = "Detected ${contradictions.size} contradictions."
        )
    }

    private fun extract(text: String, target: String): String =
        "Extracted $target info from text."

    private fun detectContradictions(statements: List<Statement>): List<Contradiction> {
        val list = mutableListOf<Contradiction>()

        statements.forEach { s1 ->
            statements.forEach { s2 ->
                if (s1.id < s2.id) {
                    if ("never" in s1.text.lowercase() && "did" in s2.text.lowercase()) {
                        list += Contradiction(
                            type = "statement",
                            description = "Contradiction: '${s1.text}' vs '${s2.text}'",
                            related = listOf(s1.id, s2.id),
                            severity = "HIGH"
                        )
                    }
                }
            }
        }

        return list
    }
}
