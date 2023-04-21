package com.example.tasktrack.login.domain

import CredentialLoginUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktrack.R
import com.example.tasktrack.login.LogInViewState
import com.example.tasktrack.login.domain.model.LoginResults
import com.example.tasktrack.ui.components.UIText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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
        val currentCredentials = _viewState.value.Credentials
        _viewState.value = LogInViewState.Submitting(
            Credentials = currentCredentials
        )

        viewModelScope.launch {
            val LoginResult = credentialLoginUseCase(currentCredentials)

            _viewState.value = when (LoginResult) {
                is LoginResults.Failure.InvalidCredentials -> {
                    LogInViewState.SubmissionError(
                        Credentials = currentCredentials,
                        errorMessage = UIText.ResourceStringText(R.string.error_invalid_credentials)
                    )
                }
                else -> _viewState.value
            }
        }
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
