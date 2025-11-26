package com.verumomnis.forensic.contradiction

object NLPUtil {
    fun extractEntities(text: String): List<String> {
        val entities = mutableListOf<String>()
        // Simple entity extraction - looks for capitalized words
        val words = text.split(" ")
        words.forEach { word ->
            if (word.isNotEmpty() && word[0].isUpperCase() && word.length > 2) {
                entities.add(word.replace(Regex("[^a-zA-Z]"), ""))
            }
        }
        return entities.distinct()
    }

    fun extractDates(text: String): List<String> {
        val dates = mutableListOf<String>()
        val datePattern = Regex("\\d{4}-\\d{2}-\\d{2}|\\d{1,2}/\\d{1,2}/\\d{2,4}")
        datePattern.findAll(text).forEach {
            dates.add(it.value)
        }
        return dates
    }

    fun classifyClaim(text: String): String {
        return when {
            text.contains("never", true) -> "negation"
            text.contains("always", true) -> "assertion"
            text.contains("?") -> "question"
            else -> "statement"
        }
    }
}
