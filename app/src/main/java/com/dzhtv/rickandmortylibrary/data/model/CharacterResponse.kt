package com.dzhtv.rickandmortylibrary.data.model

import com.google.gson.annotations.SerializedName


data class CharacterResponse(
    @SerializedName("info")
    val info: Info?,
    @SerializedName("results")
    val results: List<Character>?
)

data class Info(
    val count: Int?,
    val next: String?,
    val pages: Int?,
    val prev: String?
)

data class ErrorResponse(val message: String)