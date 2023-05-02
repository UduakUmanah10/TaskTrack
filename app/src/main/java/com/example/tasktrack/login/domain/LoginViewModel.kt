package com.example.tasktrack.login.domain

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
