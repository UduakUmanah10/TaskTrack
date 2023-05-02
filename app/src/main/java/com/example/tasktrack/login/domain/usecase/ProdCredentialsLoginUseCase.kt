package com.example.tasktrack.login.domain.usecase

import CredentialLoginUseCase
import com.example.tasktrack.core.data.CustomResult
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.model.LoginResults
import com.example.tasktrack.login.domain.repository.LoginRepository
import com.example.tasktrack.login.domain.repository.TokenRepository

/**
* this is a concrete implementation of a credential login use case
 * that would request logging in through the login repository
* */

class ProdCredentialsLoginUseCase(
    private val loginRepository: LoginRepository,
    private val authTokenRepository: TokenRepository
) : CredentialLoginUseCase {

    override suspend fun invoke(credentials: Credentials): LoginResults {
        val emptyEmail = credentials.email.value.isEmpty()
        val emptyPassword = credentials.password.value.isEmpty()

        if (emptyEmail || emptyPassword) {
            return LoginResults.Failure.EmptyCredentials(
                emptyEmail = emptyEmail,
                emptyPassword = emptyPassword
            )
        }

        val repositoryResult = loginRepository.Login(credentials)

        return when (repositoryResult) {
            is CustomResult.Success -> {
                // store auth token
                return LoginResults.Success
            }

            // is CustomResult.Error{

            //}

            else -> { return LoginResults.Failure.InvalidCredentials }
        }
    }

    private fun loginResultForError(error: Throwable): LoginResults.Failure {
        return when (error) {
            is InvalidCredentialsException -> {
                LoginResults.Failure.InvalidCredentials
            }
            else -> {
                LoginResults.Failure.Unknown
            }
        }
    }
}
class InvalidCredentialsException : Throwable()
