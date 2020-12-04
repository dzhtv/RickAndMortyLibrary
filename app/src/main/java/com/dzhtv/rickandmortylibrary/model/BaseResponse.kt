package com.dzhtv.rickandmortylibrary.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<T>?,
    @SerializedName("error")
    val error: String? = null
)

data class Info(
    val count: Int?,
    val next: String?,
    val pages: Int?,
    val prev: String?
)