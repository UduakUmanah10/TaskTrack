package com.example.tasktrack.login.domain.usecase

import CredentialLoginUseCase
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.model.LoginResults
import kotlinx.coroutines.delay

class DemoCredentialsLoginUseCase : CredentialLoginUseCase {
    @Suppress("MagicNumber")
    override suspend fun invoke(credentials: Credentials): LoginResults {
        delay(2000)
        return LoginResults.Failure.InvalidCredentials
    }
}
