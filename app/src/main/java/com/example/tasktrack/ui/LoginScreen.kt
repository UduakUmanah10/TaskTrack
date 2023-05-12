package com.example.tasktrack.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasktrack.login.LoginPage
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    loginCompleted: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val viewState = viewModel.viewState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    SideEffect {
        coroutineScope.launch {
            viewModel.completedState.receive()
            loginCompleted.invoke()
        }
    }

    LoginPage(
        viewState = viewState.value,
        onUserNameChanged = viewModel::emailChange,
        onPasswordChanged = viewModel::passwordChangeed,
        onLoginClicked = viewModel::SignInButtonClicked,
        onSignupClicked = viewModel::SignInButtonClicked
    )
}
