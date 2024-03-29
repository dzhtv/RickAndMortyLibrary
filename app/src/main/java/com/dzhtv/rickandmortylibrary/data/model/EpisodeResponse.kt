package com.dzhtv.rickandmortylibrary.data.model

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    val id: Int,
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
