package com.dzhtv.rickandmortylibrary.data.repository


import com.dzhtv.rickandmortylibrary.data.model.*

interface NetworkRepository {

    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        species: String? = null,
        type: String? = null,
        gender: CharacterGender? = null
    ): ResultWrapper<CharacterResponse>

    suspend fun getCharacter(id: Int): ResultWrapper<Character>

    suspend fun getCharacters(idList: Array<Int>): ResultWrapper<List<Character>>
}