package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.example.localization.AppLanguage

private val CupertinoDarkColorScheme = darkColorScheme(
    primary = IosBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF102A4C),
    onPrimaryContainer = Color(0xFFBFE0FF),
    secondary = IosIndigo,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF23225C),
    onSecondaryContainer = Color(0xFFE0E0FF),
    tertiary = IosGreen,
    onTertiary = Color(0xFF003816),
    tertiaryContainer = Color(0xFF0E4F26),
    onTertiaryContainer = Color(0xFF9BF6B8),
    background = DarkIosPalette.groupedBackground,
    onBackground = DarkIosPalette.textPrimary,
    surface = DarkIosPalette.card,
    onSurface = DarkIosPalette.textPrimary,
    surfaceVariant = DarkIosPalette.cardElevated,
    onSurfaceVariant = DarkIosPalette.textSecondary,
    outline = DarkIosPalette.separator,
    error = IosPink,
    onError = Color.White
)

private val CupertinoLightColorScheme = lightColorScheme(
    primary = Color(0xFF007AFF),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDCEEFF),
    onPrimaryContainer = Color(0xFF002D62),
    secondary = Color(0xFF5856D6),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE5E4FF),
    onSecondaryContainer = Color(0xFF1B196B),
    tertiary = Color(0xFF248A3D),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFD1FAE5),
    onTertiaryContainer = Color(0xFF064E3B),
    background = LightIosPalette.groupedBackground,
    onBackground = LightIosPalette.textPrimary,
    surface = LightIosPalette.card,
    onSurface = LightIosPalette.textPrimary,
    surfaceVariant = LightIosPalette.cardElevated,
    onSurfaceVariant = LightIosPalette.textSecondary,
    outline = LightIosPalette.separator,
    error = Color(0xFFE11D48),
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    language: AppLanguage = AppLanguage.ENGLISH,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val palette = if (darkTheme) DarkIosPalette else LightIosPalette
    val colorScheme = if (darkTheme) CupertinoDarkColorScheme else CupertinoLightColorScheme
    val typography = appTypographyFor(language)

    CompositionLocalProvider(LocalIosPalette provides palette) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}
