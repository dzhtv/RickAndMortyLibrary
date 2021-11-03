package com.dzhtv.rickandmortylibrary.data.model

data class CharacterResponse(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: CharacterLocationResponse,
    val name: String,
    val origin: CharacterOriginResponse,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)