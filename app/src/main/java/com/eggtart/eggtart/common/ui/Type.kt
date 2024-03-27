package com.eggtart.eggtart.common.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.eggtart.eggtart.R

private val DefaultTypography = Typography()

private val DefaultFontFamily = FontFamily(
    Font(weight = FontWeight.Black, resId = R.font.pretendard_black),
    Font(weight = FontWeight.ExtraBold, resId = R.font.pretendard_extra_bold),
    Font(weight = FontWeight.Bold, resId = R.font.pretendard_bold),
    Font(weight = FontWeight.SemiBold, resId = R.font.pretendard_semi_bold),
    Font(weight = FontWeight.Medium, resId = R.font.pretendard_medium),
    Font(weight = FontWeight.Normal, resId = R.font.pretendard_regular),
    Font(weight = FontWeight.Light, resId = R.font.pretendard_light),
    Font(weight = FontWeight.ExtraLight, resId = R.font.pretendard_extra_light),
    Font(weight = FontWeight.Thin, resId = R.font.pretendard_thin),
)

// Set of Material typography styles to start with

val Typography = Typography(
    displayLarge = DefaultTypography.displayLarge.copy(fontFamily = DefaultFontFamily),
    displayMedium = DefaultTypography.displayMedium.copy(fontFamily = DefaultFontFamily),
    displaySmall = DefaultTypography.displaySmall.copy(fontFamily = DefaultFontFamily),
    headlineLarge = DefaultTypography.headlineLarge.copy(fontFamily = DefaultFontFamily),
    headlineMedium = DefaultTypography.headlineMedium.copy(fontFamily = DefaultFontFamily),
    headlineSmall = DefaultTypography.headlineSmall.copy(fontFamily = DefaultFontFamily),
    titleLarge = DefaultTypography.titleLarge.copy(
        fontFamily = DefaultFontFamily,
        fontSize = 22.sp,
        lineHeight = 33.sp,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = DefaultTypography.titleMedium.copy(
        fontFamily = DefaultFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    titleSmall = DefaultTypography.titleSmall.copy(
        fontFamily = DefaultFontFamily,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = DefaultTypography.bodyLarge.copy(
        fontFamily = DefaultFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = DefaultTypography.bodyMedium.copy(
        fontFamily = DefaultFontFamily,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.Medium
    ),
    bodySmall = DefaultTypography.bodySmall.copy(
        fontFamily = DefaultFontFamily,
        fontSize = 12.sp,
        lineHeight = TextUnit.Unspecified,
        fontWeight = FontWeight.Medium
    ),
    labelLarge = DefaultTypography.labelLarge.copy(
        fontFamily = DefaultFontFamily,
        fontSize = 16.sp,
        lineHeight = TextUnit.Unspecified,
        fontWeight = FontWeight.Bold
    ),
    labelMedium = DefaultTypography.labelMedium.copy(
        fontFamily = DefaultFontFamily,
        fontSize = 14.sp,
        lineHeight = TextUnit.Unspecified,
        fontWeight = FontWeight.Bold
    ),
    labelSmall = DefaultTypography.labelSmall.copy(
        fontFamily = DefaultFontFamily,
        fontSize = 12.sp,
        lineHeight = TextUnit.Unspecified,
        fontWeight = FontWeight.Bold
    ),
)