package com.dzhtv.rickandmortylibrary.domain.repository

import com.dzhtv.rickandmortylibrary.domain.model.ResultWrapper
import com.dzhtv.rickandmortylibrary.domain.model.Character
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.model.Episode
import com.dzhtv.rickandmortylibrary.domain.model.EpisodeItem

interface RickAndMortyRepository {

    suspend fun loadCharacters(page: Int? = null): ResultWrapper<Character>

    suspend fun getCharacterById(id: Int): ResultWrapper<CharacterItem>

    suspend fun getCharacters(idList: Array<Int>): ResultWrapper<List<CharacterItem>>

    suspend fun getEpisodeList(): ResultWrapper<Episode>

    suspend fun getEpisodeById(id: Int): ResultWrapper<EpisodeItem>

    suspend fun addToFavorites(character: CharacterItem): Boolean

    suspend fun removeFromFavorites(characterId: Int): Boolean
}