package com.example.tasktrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasktrack.login.LogInViewState
import com.example.tasktrack.login.LoginPage
import com.example.tasktrack.login.domain.usecase.DemoCredentialsLoginUseCase
import com.example.tasktrack.ui.LoginScreen
import com.example.tasktrack.ui.LoginViewModel
import com.example.tasktrack.ui.theme.TaskTrackTheme

class MainActivity : ComponentActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private val loginViewModelFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val useCase = DemoCredentialsLoginUseCase()
            return LoginViewModel(useCase) as T
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)
        setContent {
            TaskTrackTheme {
                LoginScreen(viewModel = loginViewModel)
            }
        }
    }
}


