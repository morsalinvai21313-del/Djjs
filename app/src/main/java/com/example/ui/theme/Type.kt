package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.localization.AppLanguage

val InterFontFamily = FontFamily(
    Font(R.font.inter, FontWeight.Normal),
    Font(R.font.inter, FontWeight.Medium),
    Font(R.font.inter, FontWeight.SemiBold),
    Font(R.font.inter, FontWeight.Bold)
)

val BengaliFontFamily = FontFamily(
    Font(R.font.noto_sans_bengali, FontWeight.Normal),
    Font(R.font.noto_sans_bengali, FontWeight.Medium),
    Font(R.font.noto_sans_bengali, FontWeight.SemiBold),
    Font(R.font.noto_sans_bengali, FontWeight.Bold)
)

val CodeMonoFontFamily = FontFamily(
    Font(R.font.jetbrains_mono, FontWeight.Normal),
    Font(R.font.jetbrains_mono, FontWeight.Medium),
    Font(R.font.jetbrains_mono, FontWeight.SemiBold),
    Font(R.font.jetbrains_mono, FontWeight.Bold)
)

fun appTypographyFor(language: AppLanguage): Typography {
    val primaryFamily = when (language) {
        AppLanguage.BENGALI -> BengaliFontFamily
        AppLanguage.ENGLISH, AppLanguage.INDONESIAN -> InterFontFamily
        else -> FontFamily.Default
    }

    return Typography(
        displaySmall = TextStyle(
            fontFamily = primaryFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            letterSpacing = (-0.4).sp
        ),
        headlineMedium = TextStyle(
            fontFamily = primaryFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = (-0.3).sp
        ),
        titleLarge = TextStyle(
            fontFamily = primaryFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            lineHeight = 25.sp,
            letterSpacing = (-0.2).sp
        ),
        titleMedium = TextStyle(
            fontFamily = primaryFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.1).sp
        ),
        bodyLarge = TextStyle(
            fontFamily = primaryFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = primaryFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 19.sp,
            letterSpacing = 0.sp
        ),
        labelLarge = TextStyle(
            fontFamily = primaryFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = primaryFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.2.sp
        )
    )
}

val Typography = appTypographyFor(AppLanguage.ENGLISH)
