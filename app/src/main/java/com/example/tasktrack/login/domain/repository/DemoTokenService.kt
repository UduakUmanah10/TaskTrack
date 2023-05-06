package com.example.tasktrack.login.domain.repository

import com.example.tasktrack.login.domain.model.Token
import javax.inject.Inject

class DemoTokenService @Inject constructor() : TokenRepository {
    override suspend fun storeToken(token: Token) {
        // no impl
    }

    override suspend fun fetchToken(): Token? {
        return null
    }
}
