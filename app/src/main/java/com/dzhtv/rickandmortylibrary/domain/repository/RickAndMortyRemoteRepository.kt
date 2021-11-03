package com.dzhtv.rickandmortylibrary.domain.repository

import com.dzhtv.rickandmortylibrary.domain.model.*

interface RickAndMortyRemoteRepository {

    suspend fun getCharacters(
        page: Int? = null,
        name: String? = null,
        status: CharacterStatus? = null,
        species: String? = null,
        type: String? = null,
        gender: CharacterGender? = null
    ): ResultWrapper<CharacterFilter>

    suspend fun getCharacter(id: Int): ResultWrapper<Character>

    suspend fun getCharacters(idList: Array<Int>): ResultWrapper<List<Character>>

    suspend fun getEpisodeList(): ResultWrapper<List<Episode>>

    suspend fun getEpisode(id: Int): ResultWrapper<Episode>
}