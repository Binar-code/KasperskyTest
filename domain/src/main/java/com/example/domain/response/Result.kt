package com.example.domain.response

sealed interface Result<out T> {
    data class Ok<T>(val value: T): Result<T>
    data class Error(val error: NetworkError): Result<Nothing>
}
