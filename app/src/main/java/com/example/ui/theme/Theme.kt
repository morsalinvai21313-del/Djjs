package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DevDarkColorScheme = darkColorScheme(
    primary = AccentCyan,
    onPrimary = Color(0xFF00363D),
    primaryContainer = Color(0xFF004F58),
    onPrimaryContainer = Color(0xFF8CF4FF),

    secondary = AccentPurple,
    onSecondary = Color(0xFF380062),
    secondaryContainer = Color(0xFF550091),
    onSecondaryContainer = Color(0xFFE9D5FF),

    tertiary = AccentEmerald,
    onTertiary = Color(0xFF003822),
    tertiaryContainer = Color(0xFF005234),
    onTertiaryContainer = Color(0xFF66FFAA),

    background = DevBackground,
    onBackground = TextPrimary,

    surface = DevSurface,
    onSurface = TextPrimary,
    surfaceVariant = DevSurfaceVariant,
    onSurfaceVariant = TextSecondary,

    outline = DevSurfaceBorder,
    error = AccentRose,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Default to stunning dark developer theme
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DevDarkColorScheme,
        typography = Typography,
        content = content
    )
}
