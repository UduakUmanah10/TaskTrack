package com.example.tasktrack.login.domain.repository

import com.example.tasktrack.core.data.CustomResult
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.LoginResponse
import com.example.tasktrack.login.domain.model.AuthToken
import com.example.tasktrack.login.domain.model.Token
import com.example.tasktrack.login.domain.model.refreshToken
import javax.inject.Inject

class DemoLoginService @Inject constructor() : LoginRepository {
    override suspend fun Login(credentials: Credentials): CustomResult<LoginResponse> {
        val defaultToken = Token(
            AuthToken(""),
            refreshToken("")
        )

        val defaultResponse = LoginResponse(defaultToken)

        return CustomResult.Success(defaultResponse)
    }
}
