package com.example.tasktrack.login.domain.model

sealed class LoginResults {
    /**
     * this is the information we expect to be returned from any successful result
     * @param [authToken] this is a unique token that will allow authentication or requests
     *
     */

    data class Success(
        val authToken: String
    ) : LoginResults()

    object InvalidCredentials : LoginResults()

    /**
     * this will be returned if there is no account that match the credentials
     */
    sealed class Failure : LoginResults() {

        object InvalidCredentials : Failure()

        object Unknown : Failure()
    }
}
