package com.example.tasktrack.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.tasktrack.login.LoginPage

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val viewState = viewModel.viewState.collectAsState()

    LoginPage(
        viewState = viewState.value,
        onUserNameChanged = viewModel::emailChange,
        onPasswordChanged = viewModel::passwordChangeed,
        onLoginClicked = viewModel::SignInButtonClicked,
        onSignupClicked = viewModel::SignInButtonClicked
    )
}
