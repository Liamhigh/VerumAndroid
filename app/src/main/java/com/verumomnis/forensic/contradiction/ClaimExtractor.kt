package com.verumomnis.forensic.contradiction

object ClaimExtractor {
    fun extract(text: String): Claim {
        val id = "stmt_" + System.currentTimeMillis()
        return Claim(
            id = id,
            speaker = "user",
            content = text,
            entities = NLPUtil.extractEntities(text),
            timeRefs = NLPUtil.extractDates(text),
            claimType = NLPUtil.classifyClaim(text)
        )
    }
}
