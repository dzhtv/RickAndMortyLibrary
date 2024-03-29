package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.data.RickAndMortyApi
import com.dzhtv.rickandmortylibrary.domain.model.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RickAndMortyRemoteDataSource @Inject constructor(
    private val client: RickAndMortyApi,
    private val mapper: DtoMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseNetworkDataSource() {

    suspend fun getCharactersByFilter(
        page: Int?,
        name: String?,
        status: CharacterStatus?,
        species: String?,
        type: String?,
        gender: CharacterGender?
    ): ResultWrapper<Character> {
        val response = execute(dispatcher) {
            client.getCharactersByFilter(page = page)
        }
        return parseResult(response) {
            mapper.createCharacterFilter(it)
        }
    }

    suspend fun getCharacterById(id: Int): ResultWrapper<CharacterItem> {
        val response = client.getCharacterById(id)
        return parseResult(response) {
            mapper.createCharacterFromResponse(it)
        }
    }

    suspend fun getCharacters(idList: Array<Int>): ResultWrapper<List<CharacterItem>> {
        val response = client.getCharacterByIdList(idList)
        return parseResult(response) { characters ->
            characters.map { mapper.createCharacterFromResponse(it) }
        }
    }

    suspend fun getEpisodeList(): ResultWrapper<Episode> {
        val response = client.getEpisodes()
        return parseResult(response) { episodes ->
            mapper.createEpisode(
                info = mapper.createRequestInfo(episodes.info),
                items = episodes.results.map { mapper.createEpisodeItem(it) }
            )
        }
    }

    suspend fun getEpisodeById(id: Int): ResultWrapper<EpisodeItem> {
        val response = client.getEpisode(id)
        return parseResult(response) {
            mapper.createEpisodeItem(it)
        }
    }
}