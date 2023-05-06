package com.example.tasktrack.login.domain

@JvmInline
value class Email(val emailValue: String)
@JvmInline
value class Password(val PasswordValue: String)

data class Credentials(
    val email: Email = Email(emailValue = ""),
    val password: Password = Password(PasswordValue = "")
)
