package com.example.data.network.api

import com.example.domain.response.NetworkError
import okio.IOException
import retrofit2.HttpException
import com.example.domain.response.Result

suspend inline fun <T> safeApi(crossinline block: suspend () -> T): Result<T> = try {
    Result.Ok(block())
} catch (e: IOException) {
    Result.Error(NetworkError.NoConnection)
} catch (e: HttpException) {
    Result.Error(NetworkError.Http(e.code(), e.response()?.errorBody()?.string()))
} catch (e: Throwable) {
    Result.Error(NetworkError.Unexpected(e))
}
