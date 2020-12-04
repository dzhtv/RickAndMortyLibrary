package com.dzhtv.rickandmortylibrary.network.repository

import com.dzhtv.rickandmortylibrary.model.BaseResponse
import com.dzhtv.rickandmortylibrary.model.Character
import com.dzhtv.rickandmortylibrary.model.CharacterGender
import com.dzhtv.rickandmortylibrary.model.CharacterStatus

interface NetworkRepository {

    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        species: String? = null,
        type: String? = null,
        gender: CharacterGender? = null
    ): BaseResponse<Character>?

    suspend fun getCharacter(id: Int): Character?

    suspend fun getCharacters(idList: Array<Int>): List<Character>?
}