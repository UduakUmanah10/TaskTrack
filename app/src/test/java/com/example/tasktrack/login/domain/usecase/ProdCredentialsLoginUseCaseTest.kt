package com.example.tasktrack.login.domain.usecase
import com.example.tasktrack.core.data.CustomResult
import com.example.tasktrack.fake.FakeAuthTokenRepository
import com.example.tasktrack.fake.FakeLoginRepository
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.Email
import com.example.tasktrack.login.domain.LoginResponse
import com.example.tasktrack.login.domain.Password
import com.example.tasktrack.login.domain.model.InvalidCredentialException
import com.example.tasktrack.login.domain.model.LoginResults
import com.example.tasktrack.login.domain.model.Token
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class ProdCredentialsLoginUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testSucessfulLogin() = runBlockingTest {
        val credentials = Credentials(
            email = Email("test@uduakface.com"),
            password = Password("12345")
        )

        val tokenRepository = FakeAuthTokenRepository()

        val response = LoginResponse(Token("Success"))
        val mockresult = CustomResult.Success(response)

        val loginRepository = FakeLoginRepository().apply {
            mockLoginWithCredentials(credentials, mockresult)
        }

        val useCase = ProdCredentialsLoginUseCase(loginRepository.mock, tokenRepository.mock)
        val useCaseResult = useCase(credentials)

        assertThat(useCaseResult).isEqualTo(LoginResults.Success)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun UnknownFailureLogin() = runBlockingTest {
        val credentials = Credentials(
            email = Email("test@uduakface.com"),
            password = Password("12345")
        )
        val tokenRepository = FakeAuthTokenRepository()

        val response = LoginResponse(authToken = "Success")
        val mockresult = CustomResult.Error(Throwable("adam messed up"))

        val loginRepository = FakeLoginRepository().apply {
            mockLoginWithCredentials(credentials, mockresult)
        }

        val useCase = ProdCredentialsLoginUseCase(loginRepository.mock, tokenRepository.mock)
        val useCaseResult = useCase(credentials)

        assertThat(useCaseResult).isEqualTo(LoginResults.Failure.Unknown)
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun inValidCredentials() = runBlockingTest {
        val credentials = Credentials(
            email = Email("test@uduakface.com"),
            password = Password("12345")
        )
        val tokenRepository = FakeAuthTokenRepository()

        val mockresult = CustomResult.Error(InvalidCredentialException())

        val loginRepository = FakeLoginRepository().apply {
            mockLoginWithCredentials(credentials, mockresult)
        }

        val useCase = ProdCredentialsLoginUseCase(loginRepository.mock, tokenRepository.mock)
        val useCaseResult = useCase(credentials)

        assertThat(useCaseResult).isEqualTo(LoginResults.Failure.InvalidCredentials)
    }
}
