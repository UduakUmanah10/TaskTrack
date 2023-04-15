package com.example.tasktrack.login.domain

import CredentialLoginUseCase
import androidx.lifecycle.ViewModel
import com.example.tasktrack.login.LogInViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val credentialLoginUseCase: CredentialLoginUseCase
) : ViewModel() {

    private val _viewState: MutableStateFlow<LogInViewState> =
        MutableStateFlow(LogInViewState.InitialLoginState)

    val viewState: StateFlow<LogInViewState> = _viewState

    fun emailChange(email: String) {
        val currentCredentials = _viewState.value.Credentials
        _viewState.value = LogInViewState.Active(
            currentCredentials.withUpdatedEmail(email)

        )
    }

    fun passwordChangeed(password: String) {
        val currentCredentials = _viewState.value.Credentials
        _viewState.value = LogInViewState.Active(
            currentCredentials.withUpdatedPassword(password)
        )
    }

    fun signUpButtonClicked() {
    }

    fun SignInButtonClicked() {
    }
}

private fun Credentials.withUpdatedEmail(email: String): Credentials {
    return this.copy(
        email = Email(email)
    )
}

private fun Credentials.withUpdatedPassword(Password: String): Credentials {
    return this.copy(
        password = Password(Password)
    )
}
