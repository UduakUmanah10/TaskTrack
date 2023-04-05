package com.example.tasktrack.ui.theme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TaskTrackTheme(content: @Composable () -> Unit) {
    val colors = lightColorPallet

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}
