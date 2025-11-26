package com.verumomnis.forensic.pdf

import java.security.MessageDigest

object HashUtil {
    fun sha512(bytes: ByteArray): String {
        val digest = MessageDigest.getInstance("SHA-512")
        val hash = digest.digest(bytes)
        return hash.joinToString("") { "%02x".format(it) }
    }
}
