package com.example.tasktrack.fake

import com.example.tasktrack.core.data.CustomResult
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.LoginResponse
import com.example.tasktrack.login.domain.repository.LoginRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
/*
* this is a fake implementation of a [loginRepositor] that wraps all of our mock works
*
* */
class FakeLoginRepository {

    val mock: LoginRepository = mockk()

    fun mockLoginWithCredentials(
        credentials: Credentials,
        result: CustomResult<LoginResponse>
    ) {
        coEvery {
            mock.Login(credentials)
        } returns result
    }

    fun vreifyLoginCall() {
        coVerify(exactly = 0) {
            mock.Login(any())
        }
    }
}
