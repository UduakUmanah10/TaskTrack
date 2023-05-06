package com.example.tasktrack.login.domain.usecase
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.model.LoginResults

class SuccessCredentialsLoginUseCases : CredentialLoginUseCase {

    override suspend fun invoke(credentials: Credentials): LoginResults {
        return LoginResults.Success
    }
}
