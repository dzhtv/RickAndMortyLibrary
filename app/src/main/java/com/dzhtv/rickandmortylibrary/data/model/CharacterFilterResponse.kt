package com.dzhtv.rickandmortylibrary.data.model

import com.google.gson.annotations.SerializedName

data class CharacterFilterResponse(
    @SerializedName("info")
    val info: InfoResponse,
    @SerializedName("results")
    val results: List<CharacterResponse>
)