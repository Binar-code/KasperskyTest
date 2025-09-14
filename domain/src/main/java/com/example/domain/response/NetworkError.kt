package com.example.domain.response

sealed class NetworkError {
    data class Http(val code: Int, val body: String?): NetworkError()
    object NoConnection: NetworkError()
    data class Unexpected(val cause: Throwable): NetworkError()
}