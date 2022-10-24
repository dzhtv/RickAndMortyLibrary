package com.dzhtv.rickandmortylibrary.domain.model

data class ErrorResponse(val message: String)

fun ErrorResponse.toNetworkException() = NetworkException(errorMsg = this.message)