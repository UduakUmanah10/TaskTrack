package com.example.tasktrack.ui

import com.example.tasktrack.R
import com.example.tasktrack.ThreadExceptionHandler
import com.example.tasktrack.fake.FakeAuthTokenRepository
import com.example.tasktrack.fake.FakeLoginRepository
import com.example.tasktrack.login.LogInViewState
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.Email
import com.example.tasktrack.login.domain.Password
import com.example.tasktrack.login.domain.model.LoginResults
import com.example.tasktrack.login.domain.usecase.ProdCredentialsLoginUseCase
import com.example.tasktrack.ui.components.UIText
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    private lateinit var loginRepository: FakeLoginRepository
    private lateinit var tokenRepository: FakeAuthTokenRepository

    @get:Rule
    val uncaughtExceptionHandlerRule = ThreadExceptionHandler()

    val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        loginRepository = FakeLoginRepository()
        tokenRepository = FakeAuthTokenRepository()
        Dispatchers.setMain(dispatcher)
        testRobot = LoginViewModelRobot()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private lateinit var testRobot: LoginViewModelRobot
    private val defaultCredantials = Credentials(
        Email("uduakumanah10@gmail.com"),
        Password("Umanah4")
    )

    @Test
    fun updateCredentials() = runTest {
        val testEmail = "testy@mactest.com"
        val testPassword = "12345"

        val initialState = LogInViewState.InitialLoginState

        val emailEnteredState = LogInViewState.Active(
            Credentials(email = Email(testEmail))
        )

        val emailPasswordEnteredState = LogInViewState.Active(
            Credentials(
                email = Email(testEmail),
                password = Password(testPassword)
            )
        )

        testRobot
            .BuildViewModel()
            .assertViewStatesAfterActions(
                action = {
                    this.enterEmail(testEmail)
                    this.enterPassword(testPassword)
                },
                viewState = listOf(
                    initialState,
                    emailEnteredState,
                    emailPasswordEnteredState
                )

            )
    }

    @Test
    fun submitInvalidCredentials() = runTest {
        val testEmail = "testy@mactest.com"
        val testPassword = "12345"
        val completedCredentials = Credentials(
            email = Email(testEmail),
            password = Password(testPassword)
        )

        val initialState = LogInViewState.InitialLoginState

        val emailEnteredState = LogInViewState.Active(
            Credentials(email = Email(testEmail))
        )

        val emailPasswordEnteredState = LogInViewState.Active(
            completedCredentials
        )
        val submittingState = LogInViewState.Submitting(
            Credentials = completedCredentials
        )
        val submissionErrorState = LogInViewState.SubmissionError(
            completedCredentials,
            UIText.ResourceStringText(R.string.error_invalid_credentials)
        )

        testRobot
            .BuildViewModel()
            .mockLoginResultForCredentials(
                credentials = completedCredentials,
                LoginResults.Failure.InvalidCredentials
            )
            .assertViewStatesAfterActions(
                action = {
                    this.enterEmail(testEmail)
                    this.enterPassword(testPassword)
                    this.clickSigninButton()
                },
                viewState = listOf(
                    initialState,
                    emailEnteredState,
                    emailPasswordEnteredState,
                    submittingState,
                    submissionErrorState
                )

            )
    }

    @Test
    fun unknownError() = runTest {
        val testEmail = "testy@mactest.com"
        val testPassword = "12345"
        val completedCredentials = Credentials(
            email = Email(testEmail),
            password = Password(testPassword)
        )

        val initialState = LogInViewState.InitialLoginState

        val emailEnteredState = LogInViewState.Active(
            Credentials(email = Email(testEmail))
        )

        val emailPasswordEnteredState = LogInViewState.Active(
            completedCredentials
        )
        val submittingState = LogInViewState.Submitting(
            Credentials = completedCredentials
        )
        val submissionErrorState = LogInViewState.SubmissionError(
            completedCredentials,
            UIText.ResourceStringText(R.string.err_login_failure)
        )

        testRobot
            .BuildViewModel()
            .mockLoginResultForCredentials(
                credentials = completedCredentials,
                LoginResults.Failure.Unknown
            )
            .assertViewStatesAfterActions(
                action = {
                    this.enterEmail(testEmail)
                    this.enterPassword(testPassword)
                    this.clickSigninButton()
                },
                viewState = listOf(
                    initialState,
                    emailEnteredState,
                    emailPasswordEnteredState,
                    submittingState,
                    submissionErrorState
                )

            )
    }

    @Test
    fun testSubmitWithoutCredentials() = runTest {
        Thread.setDefaultUncaughtExceptionHandler { thread, Throwable ->
            throw Throwable
        }
        val credentials = Credentials()
        val initialState = LogInViewState.InitialLoginState
        val submitting = LogInViewState.Submitting(
            Credentials = credentials
        )
        val InvalidInputState = LogInViewState.Active(
            Credentials = Credentials(),
            emailInputErrorMessage = UIText.ResourceStringText(R.string.error_empty_email),
            passwordInputErrorMessage = UIText.ResourceStringText(R.string.error_empty_password)
        )
        testRobot
            .BuildViewModel()
            .mockLoginResultForCredentials(
                credentials = credentials,
                LoginResults.Failure.EmptyCredentials(emptyEmail = true, emptyPassword = true)
            )
            .assertViewStatesAfterActions(
                action = {
                    clickSigninButton()
                },
                listOf(initialState, submitting, InvalidInputState)
            )
    }

    @Test
    fun testClearErrorsAfterInput() = runTest {
        val credentials = Credentials()
        val testEmail = "testy@mctestface.com"
        val testPassword = ""

        val initialState = LogInViewState.InitialLoginState
        val submittingState = LogInViewState.Submitting(
            Credentials = credentials
        )
        val invalidInputState = LogInViewState.Active(
            Credentials = credentials,
            emailInputErrorMessage = UIText.ResourceStringText(R.string.error_empty_email),
            passwordInputErrorMessage = UIText.ResourceStringText(R.string.error_empty_password)
        )
        val emailInputState = LogInViewState.Active(
            Credentials = Credentials(email = Email(testEmail)),
            emailInputErrorMessage = null,
            passwordInputErrorMessage = UIText.ResourceStringText(R.string.error_empty_password)
        )
        val passwordInputState = LogInViewState.Active(
            Credentials = Credentials(email = Email(testEmail), password = Password(testPassword)),
            emailInputErrorMessage = null,
            passwordInputErrorMessage = null
        )

        testRobot
            .BuildViewModel()
            .mockLoginResultForCredentials(
                credentials = credentials,
                LoginResults.Failure.EmptyCredentials(emptyEmail = true, emptyPassword = true)
            )
            .assertViewStatesAfterActions(
                action = {
                    clickSigninButton()
                },
                viewState = listOf(
                    initialState,
                    submittingState,
                    invalidInputState
                )
            )
    }

    @Test
    fun testEmptyCredentialsLogin() = runTest {
        val emptyCredentials = Credentials()
        val useCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            authTokenRepository = tokenRepository.mock
        )
        val result = useCase(emptyCredentials)
        assertThat(result).isEqualTo(LoginResults.Failure.EmptyCredentials(true, true))
        loginRepository.vreifyLoginCall()
        tokenRepository.verifyNoTokenStored()
    }
}
