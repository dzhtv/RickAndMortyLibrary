package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.data.RickAndMortyApi
import com.dzhtv.rickandmortylibrary.data.model.CharacterFilterResponse
import com.dzhtv.rickandmortylibrary.data.model.CharacterResponse
import com.dzhtv.rickandmortylibrary.data.model.EpisodeListResponse
import com.dzhtv.rickandmortylibrary.data.model.EpisodeResponse
import com.dzhtv.rickandmortylibrary.domain.model.CharacterGender
import com.dzhtv.rickandmortylibrary.domain.model.CharacterStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class RickAndMortyRemoteDataSource @Inject constructor(
    private val client: RickAndMortyApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseNetworkHandler() {

    suspend fun getCharactersByFilter(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        species: String? = null,
        type: String? = null,
        gender: CharacterGender? = null
    ): Response<CharacterFilterResponse> {
        return execute(dispatcher) {
            client.getCharactersByFilter(page = page)
        }
    }

    suspend fun getCharacterById(id: Int): Response<CharacterResponse> {
        return execute(dispatcher) {
            client.getCharacterById(id)
        }
    }

    suspend fun getCharacters(idList: List<Int>): Response<List<CharacterResponse>> {
        return execute(dispatcher) {
            client.getCharacterByIdList(idList)
        }
    }

    suspend fun getEpisodeList(): Response<EpisodeListResponse> {
        return execute(dispatcher) {
            client.getEpisodes()
        }
    }

    suspend fun getEpisodeById(id: Int): Response<EpisodeResponse> {
        return execute(dispatcher) {
            client.getEpisode(id)
        }
    }
}