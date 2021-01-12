package com.dzhtv.rickandmortylibrary.data.repository

import com.dzhtv.rickandmortylibrary.data.model.BaseResponse
import com.dzhtv.rickandmortylibrary.data.model.Character
import com.dzhtv.rickandmortylibrary.data.model.CharacterGender
import com.dzhtv.rickandmortylibrary.data.model.CharacterStatus
import com.dzhtv.rickandmortylibrary.data.CharacterService
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val client: CharacterService
) : NetworkRepository {

    override suspend fun getCharacters(
        page: Int?,
        name: String?,
        status: CharacterStatus?,
        species: String?,
        type: String?,
        gender: CharacterGender?
    ): BaseResponse<Character>? {
        return client.getCharacters(page = page).body()
    }

    override suspend fun getCharacter(id: Int): Character? {
        return client.getCharacterById(id).body()
    }

    override suspend fun getCharacters(idList: Array<Int>): List<Character>? {
        return client.getCharacterByIdList(idList.toString()).body()
    }
}