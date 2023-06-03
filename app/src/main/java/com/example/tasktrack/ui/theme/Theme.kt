
import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.BuildCompat
import com.example.tasktrack.ui.theme.AppTypography
import com.example.tasktrack.ui.theme.DarkMD2Colors
import com.example.tasktrack.ui.theme.DarkThemeColors
import com.example.tasktrack.ui.theme.LightMD2Colors
import com.example.tasktrack.ui.theme.LightThemeColors

@SuppressLint("NewApi")
@Composable
fun TaskTrackTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    dynamic: Boolean = Build.VERSION.SDK_INT > 31,
    content: @Composable () -> Unit,
) {
    val colors = when {
        dynamic && isDark -> dynamicDarkColorScheme(LocalContext.current)
        dynamic && !isDark -> dynamicLightColorScheme(LocalContext.current)
        isDark -> DarkThemeColors
        else -> LightThemeColors
    }

    androidx.compose.material.MaterialTheme(
        colors = if (isDark) {
            DarkMD2Colors
        } else {
            LightMD2Colors
        }
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = AppTypography,
            content = content
        )
    }
}
