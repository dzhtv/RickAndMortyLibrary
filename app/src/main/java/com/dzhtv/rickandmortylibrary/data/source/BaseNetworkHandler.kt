package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.domain.model.ErrorResponse
import com.dzhtv.rickandmortylibrary.domain.model.NetworkException
import com.dzhtv.rickandmortylibrary.domain.model.toNetworkException
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import java.lang.Exception

open class BaseNetworkHandler {

    suspend fun <T> execute(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> Response<T>
    ): Response<T> {
        return withContext(dispatcher) {
            apiCall.invoke()
        }
    }

    fun <T, K> parseResult(
        response: Response<T>,
        mapperCall: (T) -> K
    ): K {
        return try {
            if (response.isSuccessful) {
                mapperCall.invoke(response.body()!!)
            } else {
                throw convertErrorBody(
                    code = response.code(),
                    errorBody = response.errorBody()
                ).toNetworkException()
            }
        } catch (t: Throwable) {
            throw NetworkException("Unknown error")
        }
    }

    private fun convertErrorBody(code: Int, errorBody: ResponseBody?): ErrorResponse {
        return try {
            errorBody?.string()?.let {
                return Gson().fromJson(it, ErrorResponse::class.java)
            } ?: run {
                when (code) {
                    in 400..500 -> {
                        ErrorResponse("Network error")
                    }
                    else -> {
                        ErrorResponse("Unknown error")
                    }
                }
            }
        } catch (ex: Exception) {
            ErrorResponse(message = ex.message ?: "Unknown error")
        }
    }

    fun <T> parseResult(response: Response<T>): T {
        return try {
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw convertErrorBody(
                    code = response.code(),
                    errorBody = response.errorBody()
                ).toNetworkException()
            }
        } catch (throwable: Throwable) {
            throw NetworkException("Unknown error")
        }
    }
}