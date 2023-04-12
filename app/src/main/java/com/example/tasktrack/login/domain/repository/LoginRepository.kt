package com.example.tasktrack.login.domain.repository

import com.example.tasktrack.core.data.CustomResult
import com.example.tasktrack.login.domain.Credentials
import com.example.tasktrack.login.domain.LoginResponse

/**
 * this is a data layer for any request related to logging in the user
* */

interface LoginRepository {

    suspend fun Login(
        credentials: Credentials
    ): CustomResult<LoginResponse>


}
