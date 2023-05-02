package com.example.tasktrack.login

import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.ui.components.UIText

/**
 * This class Defines the current and possible state of the log in screen
 * @param[userName] the current text entered into the ui text field
 * @param[password] the current password entered in the password field
 *
 *
 */

sealed class LogInViewState(
    open val Credentials: Credentials,
    open val buttonsEnabled: Boolean = true
) {
    object InitialLoginState : LogInViewState(
        Credentials = Credentials()
    )

    data class Active(
        override val Credentials: Credentials,
        val emailInputErrorMessage: UIText? = null,
        val passwordInputErrorMessage: UIText? = null
    ) : LogInViewState(
        Credentials = Credentials
    )

    data class Submitting(
        override val Credentials: Credentials
    ) : LogInViewState(
        Credentials = Credentials,
        buttonsEnabled = false
    )

    data class SubmissionError(
        override val Credentials: Credentials,
        val errorMessage: UIText
    ) : LogInViewState(
        Credentials = Credentials
    )


}
