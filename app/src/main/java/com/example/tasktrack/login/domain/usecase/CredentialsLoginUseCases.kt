package com.example.tasktrack.login.domain.usecase
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.model.LoginResults

interface CredentialLoginUseCase {
    /**
     * This function consumes a :
     * @param[credentials]
     * @return[LoginResults] that contains a LoginResponse if successful or an Error if not
     **/

    suspend operator fun invoke(
        credentials: Credentials
    ): LoginResults
}

