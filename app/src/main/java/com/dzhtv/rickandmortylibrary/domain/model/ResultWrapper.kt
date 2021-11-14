package com.dzhtv.rickandmortylibrary.domain.model


sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T): ResultWrapper<T>()
    data class Error(val error: ErrorResponse): ResultWrapper<Nothing>()
}
