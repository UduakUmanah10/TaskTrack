package com.example.tasktrack.login.domain.usecase

import Email
import LoginUseCase
import Password
import com.example.tasktrack.login.domain.model.LoginResults

class SuccessLoginUseCases : LoginUseCase {
    override suspend fun invoke(email: Email, password: Password): LoginResults {
        return LoginResults.Success
    }
}
