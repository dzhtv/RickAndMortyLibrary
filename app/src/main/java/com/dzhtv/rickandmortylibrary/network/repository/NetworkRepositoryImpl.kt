package com.dzhtv.rickandmortylibrary.network.repository

import com.dzhtv.rickandmortylibrary.model.Character
import com.dzhtv.rickandmortylibrary.model.CharacterGender
import com.dzhtv.rickandmortylibrary.model.CharacterStatus
import com.dzhtv.rickandmortylibrary.network.CharacterService
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
    ): List<Character>? {
        return client.getCharacters().body()?.results
    }

    override suspend fun getCharacter(id: Int): Character? {
        return client.getCharacterById(id).body()
    }

    override suspend fun getCharacters(idList: Array<Int>): List<Character>? {
        return client.getCharacterByIdList(idList.toString()).body()
    }
}