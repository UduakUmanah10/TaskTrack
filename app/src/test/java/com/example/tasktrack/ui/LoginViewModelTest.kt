package com.example.tasktrack.ui

import com.example.tasktrack.login.LogInViewState
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.Email
import com.example.tasktrack.login.domain.Password
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var testRobot: LoginViewModelRobot
    private val defaultCredantials = Credentials(
        Email("uduakumanah10@gmail.com"),
        Password("Umanah4")
    )

    @Before
    fun setUp() {
        testRobot = LoginViewModelRobot()
    }

    @Test
    fun testInitialState() {
        testRobot
            .BuildViewModel()
            .assertViewState(LogInViewState.InitialLoginState)
    }

    @Test
    fun updateCredentials() {
        val credentials = defaultCredantials

        testRobot
            .BuildViewModel()
            .enterEmail(credentials.email.email)
            .enterPassword(credentials.password.password)
            .assertViewState(LogInViewState.Active(credentials))
    }
}


