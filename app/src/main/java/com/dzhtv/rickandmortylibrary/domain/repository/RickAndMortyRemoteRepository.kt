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
    ): ResultWrapper<Character>

    suspend fun getCharacter(id: Int): ResultWrapper<CharacterItem>

    suspend fun getCharacters(idList: Array<Int>): ResultWrapper<List<CharacterItem>>

    suspend fun getEpisodeList(): ResultWrapper<Episode>

    suspend fun getEpisode(id: Int): ResultWrapper<EpisodeItem>
}