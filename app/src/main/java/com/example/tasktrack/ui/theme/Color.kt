@file: Suppress("MagicNumber")

package com.example.tasktrack.ui.theme
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val backgroundColor = Color(0xFF2b2b2a)
val cardBackground = Color(0xFFe6f5fb)
val fontColor = Color(0xFFe8e9e9)

val lightColorPallet = lightColors(
    onPrimary = Color.White,
    background = backgroundColor,
    onSurface = backgroundColor,
    surface = fontColor,
    secondary = cardBackground

)
