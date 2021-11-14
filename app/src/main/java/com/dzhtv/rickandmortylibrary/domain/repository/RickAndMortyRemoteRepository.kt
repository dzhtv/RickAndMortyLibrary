package com.dzhtv.rickandmortylibrary.domain.repository

import com.dzhtv.rickandmortylibrary.domain.model.*

interface RickAndMortyRemoteRepository {

    suspend fun getCharactersByFilter(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        species: String? = null,
        type: String? = null,
        gender: CharacterGender? = null
    ): ResultWrapper<Character>

    suspend fun getCharacterById(id: Int): ResultWrapper<CharacterItem>

    suspend fun getCharacters(idList: Array<Int>): ResultWrapper<List<CharacterItem>>

    suspend fun getEpisodeList(): ResultWrapper<Episode>

    suspend fun getEpisodeById(id: Int): ResultWrapper<EpisodeItem>
}