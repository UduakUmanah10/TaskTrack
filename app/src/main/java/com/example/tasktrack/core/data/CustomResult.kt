package com.example.tasktrack.core.data

sealed class CustomResult<out T> {
    data class Success<out T>(val data: T) : CustomResult<T>()
    data class Error(val data: Throwable) : CustomResult<Nothing>()
}
