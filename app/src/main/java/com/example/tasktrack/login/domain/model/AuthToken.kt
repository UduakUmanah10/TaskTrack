package com.example.tasktrack.login.domain.model


@JvmInline
value class AuthToken(val value: String)

@JvmInline
value class refreshToken(val value: String)

data class Token(
    val authToken: AuthToken,
    val refreshToken: refreshToken
)
