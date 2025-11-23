package com.verum.android

object StatementParser {

    fun parse(input: String): List<Statement> {
        val lines = input.split("\n")
        val statements = mutableListOf<Statement>()

        lines.forEachIndexed { index, line ->
            if (line.isNotBlank()) {
                statements += Statement(
                    id = index + 1,
                    speaker = detectSpeaker(line),
                    text = line.trim(),
                    timestamp = detectTimestamp(line)
                )
            }
        }

        return statements
    }

    private fun detectSpeaker(line: String): String? {
        val colon = line.indexOf(":")
        return if (colon > 0 && colon < 40) line.substring(0, colon).trim()
        else null
    }

    private fun detectTimestamp(line: String): String? {
        val pattern = Regex("\\d{4}-\\d{2}-\\d{2}")
        return if (pattern.containsMatchIn(line)) {
            pattern.find(line)!!.value
        } else null
    }
}
