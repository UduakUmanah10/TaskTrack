package com.example.tasktrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasktrack.ui.LoginScreen
import com.example.tasktrack.ui.LoginViewModel
import com.example.tasktrack.ui.theme.TaskTrackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TaskTrackTheme {
                val viewModel: LoginViewModel = viewModel()
                LoginScreen(viewModel = viewModel)
            }
        }
    }
}
