package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.domain.model.ErrorResponse
import com.dzhtv.rickandmortylibrary.domain.model.ResultWrapper
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

open class BaseNetworkDataSource {

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
    ): ResultWrapper<K> {
        return try {
            if (response.isSuccessful) {
                ResultWrapper.Success(
                    mapperCall(response.body()!!)
                )
            } else {
                ResultWrapper.Error(
                    convertErrorBody(
                        code = response.code(),
                        errorBody = response.errorBody()
                    )
                )
            }
        } catch (t: Throwable) {
            ResultWrapper.Error(
                ErrorResponse(message = "Unknown error")
            )
        }
    }

    private fun convertErrorBody(code: Int, errorBody: ResponseBody?): ErrorResponse {
        return try {
            errorBody?.string()?.let {
                return Gson().fromJson(it, ErrorResponse::class.java)
            } ?: run {
                when(code) {
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

//    suspend fun <T> execute(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultWrapper<T> {
//        return withContext(dispatcher) {
//            try {
//                ResultWrapper.Success(apiCall.invoke())
//            } catch (throwable: Throwable) {
//                when(throwable) {
//                    is IOException -> ResultWrapper.NetworkError
//                    is HttpException -> {
//                        val code = throwable.code()
//                        val errorResponse = convertErrorBody(throwable)
//                        ResultWrapper.GenericError(code, errorResponse)
//                    }
//                    else -> {
//                        ResultWrapper.GenericError(null, null)
//                    }
//                }
//            }
//        }
//    }

}