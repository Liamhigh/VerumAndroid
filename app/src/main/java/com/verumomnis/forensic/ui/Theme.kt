package com.verumomnis.forensic.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.verumomnis.forensic.ui.theme.*

@Composable
fun VerumTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = VO_AccentBlue,
            onPrimary = Color.White,
            background = VO_Black,
            onBackground = Color.White,
            surface = VO_DeepBlue,
            onSurface = Color.White,
            error = VO_Red
        ),
        typography = Typography,
        content = content
    )
}

// Keep VoTheme as alias for backward compatibility
@Composable
fun VoTheme(content: @Composable () -> Unit) = VerumTheme(content)
