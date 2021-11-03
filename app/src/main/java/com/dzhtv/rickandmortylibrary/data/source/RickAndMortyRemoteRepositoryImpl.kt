package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.data.RickAndMortyApi
import com.dzhtv.rickandmortylibrary.domain.model.*
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RickAndMortyRemoteRepositoryImpl @Inject constructor(
    private val client: RickAndMortyApi,
    private val mapper: ApiMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RickAndMortyRemoteRepository, BaseNetworkRepository() {

    override suspend fun getCharacters(
        page: Int?,
        name: String?,
        status: CharacterStatus?,
        species: String?,
        type: String?,
        gender: CharacterGender?
    ): ResultWrapper<CharacterFilter> {
        return execute(dispatcher) {
            mapper.createCharacterFilter(client.getCharactersByFilter(page = page))
        }
    }

    override suspend fun getCharacter(id: Int): ResultWrapper<Character> {
       return execute(dispatcher) {
           mapper.createCharacterFromResponse(client.getCharacterById(id))
       }
    }

    override suspend fun getCharacters(idList: Array<Int>): ResultWrapper<List<Character>> {
        return execute(dispatcher) {
            val response = client.getCharacterByIdList(idList.toString())
            response.map { mapper.createCharacterFromResponse(it) }
        }
    }

    override suspend fun getEpisodeList(): ResultWrapper<List<Episode>> {
        return execute(dispatcher) {
            val response = client.getEpisodes()
            response.results.map { mapper.createCharacterEpisode(it) }
        }
    }

    override suspend fun getEpisode(id: Int): ResultWrapper<Episode> {
        return execute(dispatcher) {
            mapper.createCharacterEpisode(client.getEpisode(id))
        }
    }
}