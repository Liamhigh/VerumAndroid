package com.verumomnis.forensic.contradiction

object BehaviourBrain {
    fun detectStressMarkers(text: String): List<String> {
        val flags = mutableListOf<String>()
        if (text.contains("I don't remember", true)) {
            flags.add("Avoidance marker")
        }
        if (text.contains("why are you asking", true)) {
            flags.add("Defensive language spike")
        }
        return flags
    }
}
