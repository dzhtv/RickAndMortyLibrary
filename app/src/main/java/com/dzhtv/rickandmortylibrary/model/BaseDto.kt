package com.dzhtv.rickandmortylibrary.model

data class BaseResponse<T>(
    val info: Info?,
    val results: List<T>?
)

data class Info(
    val count: Int?,
    val next: String?,
    val pages: Int?,
    val prev: String?
)