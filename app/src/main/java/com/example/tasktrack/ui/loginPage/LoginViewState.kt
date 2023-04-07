package com.example.tasktrack.ui.loginPage

/**
 * This class Defines the current and possible state of the log in screem
 * @param[userName] the current text entered into the ui text field
 * @param[password] the current password entered in the password field
 *
 *
 */

data class LoginViewState(
    val userName: String,
    val password: String
)
