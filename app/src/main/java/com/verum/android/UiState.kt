package com.verum.android

data class Statement(
    val id: Int,
    val speaker: String?,
    val text: String,
    val timestamp: String? = null
)
