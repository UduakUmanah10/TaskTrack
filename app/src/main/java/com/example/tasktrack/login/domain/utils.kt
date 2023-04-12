package com.example.tasktrack.login.domain
@Suppress("UnusedPrivateMember")
@JvmInline
value class Email(val email: String)

@Suppress("UnusedPrivateMember")
@JvmInline
value class Password(val password: String)

data class Credentials(
    val email: Email = Email(email = ""),
    val password: Password = Password(password = "")
)
