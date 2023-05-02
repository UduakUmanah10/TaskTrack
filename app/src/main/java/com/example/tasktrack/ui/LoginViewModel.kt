package com.example.tasktrack.ui

import CredentialLoginUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktrack.R
import com.example.tasktrack.login.LogInViewState
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.Email
import com.example.tasktrack.login.domain.Password
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
        val currentPasswordErrorMessage =
            (_viewState.value as? LogInViewState.Active)?.emailInputErrorMessage

        _viewState.value = LogInViewState.Active(
            Credentials = currentCredentials.withUpdatedEmail(email),
            emailInputErrorMessage = null,
            passwordInputErrorMessage = currentPasswordErrorMessage
        )
    }

    fun passwordChangeed(password: String) {
        val currentCredentials = _viewState.value.Credentials

        val currentPasswordErrorMessage =
            (_viewState.value as? LogInViewState.Active)?.passwordInputErrorMessage

        _viewState.value = LogInViewState.Active(
            Credentials = currentCredentials.withUpdatedPassword(password),
            emailInputErrorMessage = currentPasswordErrorMessage,
            passwordInputErrorMessage = null
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
                is LoginResults.Failure.Unknown -> {
                    LogInViewState.SubmissionError(
                        Credentials = currentCredentials,
                        errorMessage = UIText.ResourceStringText(R.string.err_login_failure)
                    )
                }
                is LoginResults.Failure.EmptyCredentials -> {
                    LogInViewState.Active(
                        Credentials = currentCredentials,
                        emailInputErrorMessage = if (LoginResult.emptyEmail) {
                            UIText.ResourceStringText(R.string.error_empty_email)
                        } else null,

                        passwordInputErrorMessage = if (LoginResult.emptyPassword) {
                            UIText.ResourceStringText(R.string.error_empty_password)
                        } else null

                    )
                }
                else -> _viewState.value
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
}
