package com.example.tasktrack.fake

import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.model.LoginResults
import com.example.tasktrack.login.domain.usecase.CredentialLoginUseCase
import io.mockk.coEvery
import io.mockk.mockk

class FakeCredentialsLoginUseCase {
    val mock: CredentialLoginUseCase = mockk()

    fun mockLoginresultForCredentials(
        credentials: Credentials,
        result: LoginResults
    ) {
        coEvery {
            mock(credentials)
        } returns result
    }
}
