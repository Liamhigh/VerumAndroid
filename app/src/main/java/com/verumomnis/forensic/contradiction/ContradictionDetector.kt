package com.verumomnis.forensic.contradiction

object ContradictionDetector {
    fun check(newClaim: Claim, history: List<Claim>): List<String> {
        val contradictions = mutableListOf<String>()
        history.forEach { old ->
            if (old.speaker == newClaim.speaker && old.claimType == newClaim.claimType &&
                old.entities.intersect(newClaim.entities.toSet()).isNotEmpty()
            ) {
                if (old.content.contains("never", true) &&
                    newClaim.content.contains(old.entities.first(), true)
                ) {
                    contradictions.add(
                        "Direct contradiction with previous claim: '${old.content}'"
                    )
                }
            }
        }
        return contradictions
    }
}
