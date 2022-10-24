package com.dzhtv.rickandmortylibrary.domain.repository

import com.dzhtv.rickandmortylibrary.domain.model.Character
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.model.Episode
import com.dzhtv.rickandmortylibrary.domain.model.EpisodeItem

interface RickAndMortyRepository {

    suspend fun loadCharacters(page: Int? = null): Character

    suspend fun getCharacterById(id: Int): CharacterItem

    suspend fun getCharacters(idList: List<Int>): List<CharacterItem>

    suspend fun getEpisodeList(): Episode

    suspend fun getEpisodeById(id: Int): EpisodeItem

    suspend fun getFavorites(): List<CharacterItem>

    suspend fun addToFavorites(character: CharacterItem)

    suspend fun findFavoriteCharacter(id: Int): CharacterItem?

    suspend fun removeFromFavorites(character: CharacterItem)
}