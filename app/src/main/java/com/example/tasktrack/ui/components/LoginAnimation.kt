package com.example.tasktrack.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.tasktrack.R

@Composable
fun LoginAnimation() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.login2))
    val isPlaying by remember { mutableStateOf(false) }
    val speed by remember { mutableStateOf(1f) }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = true
    )
    Box(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )
    }
}
