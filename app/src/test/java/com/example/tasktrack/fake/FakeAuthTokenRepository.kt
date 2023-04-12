package com.example.tasktrack.fake

import com.example.tasktrack.login.domain.model.Token
import com.example.tasktrack.login.domain.repository.TokenRepository
import io.mockk.coVerify
import io.mockk.mockk

class FakeAuthTokenRepository {

    val mock: TokenRepository = mockk()

    fun verifyTokenStored(token: Token) {
        coVerify {
            mock.storeToken(token)
        }
    }

    fun verifyNoTokenStored(token: Token) {
        coVerify(exactly = 0) {
            mock.storeToken(any())
        }
    }
}
