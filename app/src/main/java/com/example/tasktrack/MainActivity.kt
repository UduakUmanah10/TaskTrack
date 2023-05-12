package com.example.tasktrack

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tasktrack.ui.LoginScreen
import com.example.tasktrack.ui.theme.TaskTrackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TaskTrackTheme {
                LoginScreen(
                    loginCompleted = {
                        val intent = Intent(this@MainActivity, TaskActivity::class.java).also {
                            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        }
                        startActivity(intent)
                    }

                )
            }
        }
    }
}
