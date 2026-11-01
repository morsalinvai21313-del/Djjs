package com.example.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Cupertino / iOS 18 System Accent Palette
val IosBlue = Color(0xFF0A84FF)
val IosCyan = Color(0xFF32ADE6)
val IosIndigo = Color(0xFF5E5CE6)
val IosGreen = Color(0xFF30D158)
val IosOrange = Color(0xFFFF9F0A)
val IosPink = Color(0xFFFF375F)
val IosPurple = Color(0xFFBF5AF2)
val IosTeal = Color(0xFF64D2FF)

// Legacy semantic aliases tuned to refined Cupertino Dark Pro
val DevBackground = Color(0xFF000000)
val DevSurface = Color(0xFF14161F)
val DevSurfaceVariant = Color(0xFF1E2130)
val DevSurfaceBorder = Color(0x1FFFFFFF)
val DevCodeBackground = Color(0xFF0B0D14)

val AccentCyan = IosCyan
val AccentPurple = IosPurple
val AccentEmerald = IosGreen
val AccentAmber = IosOrange
val AccentRose = IosPink

val TextPrimary = Color(0xFFF5F5F7)
val TextSecondary = Color(0xFF98989F)
val TextMuted = Color(0xFF6E6E73)

@Immutable
data class IosColorPalette(
    val isDark: Boolean,
    val background: Color,
    val groupedBackground: Color,
    val card: Color,
    val cardElevated: Color,
    val pillBackground: Color,
    val pillSelected: Color,
    val separator: Color,
    val glassBar: Color,
    val codeBackground: Color,
    val codeHeader: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,
    val primaryBlue: Color,
    val cyan: Color,
    val indigo: Color,
    val green: Color,
    val orange: Color,
    val pink: Color,
    val purple: Color
)

val DarkIosPalette = IosColorPalette(
    isDark = true,
    background = Color(0xFF000000),
    groupedBackground = Color(0xFF090B10),
    card = Color(0xFF151821),
    cardElevated = Color(0xFF1F2330),
    pillBackground = Color(0xFF1C1F2B),
    pillSelected = Color(0xFF2C3246),
    separator = Color(0x1AFFFFFF),
    glassBar = Color(0xEB11131B),
    codeBackground = Color(0xFF0B0D14),
    codeHeader = Color(0xFF141824),
    textPrimary = Color(0xFFF5F5F7),
    textSecondary = Color(0xFFA1A1AA),
    textMuted = Color(0xFF6E6E77),
    primaryBlue = Color(0xFF0A84FF),
    cyan = Color(0xFF32ADE6),
    indigo = Color(0xFF5E5CE6),
    green = Color(0xFF30D158),
    orange = Color(0xFFFF9F0A),
    pink = Color(0xFFFF375F),
    purple = Color(0xFFBF5AF2)
)

val LightIosPalette = IosColorPalette(
    isDark = false,
    background = Color(0xFFF2F2F7),
    groupedBackground = Color(0xFFF2F2F7),
    card = Color(0xFFFFFFFF),
    cardElevated = Color(0xFFF7F7FA),
    pillBackground = Color(0xFFE5E5EA),
    pillSelected = Color(0xFFFFFFFF),
    separator = Color(0x14000000),
    glassBar = Color(0xF0F9F9FB),
    codeBackground = Color(0xFF0F131F),
    codeHeader = Color(0xFF181E2E),
    textPrimary = Color(0xFF111114),
    textSecondary = Color(0xFF5A5A66),
    textMuted = Color(0xFF8E8E93),
    primaryBlue = Color(0xFF007AFF),
    cyan = Color(0xFF0088CC),
    indigo = Color(0xFF5856D6),
    green = Color(0xFF248A3D),
    orange = Color(0xFFD97706),
    pink = Color(0xFFE11D48),
    purple = Color(0xFF9333EA)
)

val LocalIosPalette = staticCompositionLocalOf { DarkIosPalette }
