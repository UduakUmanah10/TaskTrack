package com.example.tasktrack.ui

import com.example.tasktrack.fake.FakeCredentialsLoginUseCase
import com.example.tasktrack.login.LogInViewState
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.LoginViewModel
import com.example.tasktrack.login.domain.model.LoginResults
import com.google.common.truth.Truth.assertThat

class LoginViewModelRobot {
    private val fakeCrekentialsLogInUsecase = FakeCredentialsLoginUseCase()

    private lateinit var viewModel: LoginViewModel

    fun BuildViewModel() = apply {
        viewModel = LoginViewModel(
            credentialLoginUseCase = fakeCrekentialsLogInUsecase.mock

        )
    }

    fun mockLoginResultForCredentials(
        credentials: Credentials,
        result: LoginResults
    ) = apply {
        fakeCrekentialsLogInUsecase.mockLoginresult(credentials, result)
    }

    fun enterEmail(email: String) = apply {
        viewModel.emailChange(email)
    }

    fun enterPassword(password: String) = apply {
        viewModel.passwordChangeed(password)
    }

    fun clickSigninButton() = apply {
        viewModel.SignInButtonClicked()
    }

    fun clickSignupButton() = apply {
        viewModel.SignInButtonClicked()
    }

    fun assertViewState(expectedViewState: LogInViewState) {
        val viewmodelState = viewModel.viewState.value
        assertThat(viewmodelState).isEqualTo(expectedViewState)
    }
}