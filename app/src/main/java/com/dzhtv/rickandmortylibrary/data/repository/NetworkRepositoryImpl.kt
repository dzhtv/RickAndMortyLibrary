package com.dzhtv.rickandmortylibrary.data.repository

import com.dzhtv.rickandmortylibrary.data.CharacterService
import com.dzhtv.rickandmortylibrary.data.model.*
import com.dzhtv.rickandmortylibrary.data.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val client: CharacterService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : NetworkRepository {

    override suspend fun getCharacters(
        page: Int?,
        name: String?,
        status: CharacterStatus?,
        species: String?,
        type: String?,
        gender: CharacterGender?
    ): ResultWrapper<CharacterResponse> {
        return safeApiCall(dispatcher) {
            client.getCharacters(page = page)
        }
    }

    override suspend fun getCharacter(id: Int): ResultWrapper<Character> {
       return safeApiCall(dispatcher) {
           client.getCharacterById(id)
       }
    }

    override suspend fun getCharacters(idList: Array<Int>): ResultWrapper<List<Character>> {
        return safeApiCall(dispatcher) {
            client.getCharacterByIdList(idList.toString())
        }
    }
}