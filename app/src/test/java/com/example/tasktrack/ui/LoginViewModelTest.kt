package com.example.tasktrack.ui

import com.example.tasktrack.R
import com.example.tasktrack.login.LogInViewState
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.Email
import com.example.tasktrack.login.domain.Password
import com.example.tasktrack.login.domain.model.LoginResults
import com.example.tasktrack.ui.components.UIText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    val dispatcher = StandardTestDispatcher()

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

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        testRobot = LoginViewModelRobot()
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
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
