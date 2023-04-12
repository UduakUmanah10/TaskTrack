package com.example.tasktrack.login.domain

/**
 * this is a response from any request
 * to login to an external service
 *
 * @constructor Create [LoginResponse]
 * @property authToken
 */

data class LoginResponse( val authToken: String)

