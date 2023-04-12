package com.example.tasktrack.login.domain.repository

import com.example.tasktrack.login.domain.model.Token

interface TokenRepository {

    suspend fun storeToken(
        token: Token
    )
    suspend fun fetchToken(): Token?
}
