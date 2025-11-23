package com.verum.android

import android.content.Context
import android.net.Uri

object FileUtils {

    fun readTextFromUri(context: Context, uri: Uri): String {
        return context.contentResolver.openInputStream(uri)?.bufferedReader()?.use {
            it.readText()
        } ?: ""
    }
}
