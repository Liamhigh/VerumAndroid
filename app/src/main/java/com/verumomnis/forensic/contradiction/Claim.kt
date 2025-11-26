package com.verumomnis.forensic.contradiction

data class Claim(
    val id: String,
    val speaker: String,
    val content: String,
    val entities: List<String>,
    val timeRefs: List<String>,
    val claimType: String
)
