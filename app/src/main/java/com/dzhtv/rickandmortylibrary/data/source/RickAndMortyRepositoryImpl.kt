package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.domain.model.ResultWrapper
import com.dzhtv.rickandmortylibrary.domain.model.Character
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.model.Episode
import com.dzhtv.rickandmortylibrary.domain.model.EpisodeItem
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import com.dzhtv.rickandmortylibrary.toLog
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RickAndMortyRemoteDataSource,
    private val localDataSource: RickAndMortyLocalDataSource
) : RickAndMortyRepository {

    override suspend fun loadCharacters(page: Int?): ResultWrapper<Character> {
        return remoteDataSource.getCharactersByFilter(page)
    }

    override suspend fun getCharacterById(id: Int): ResultWrapper<CharacterItem> {
        return remoteDataSource.getCharacterById(id)
    }

    override suspend fun getCharacters(idList: List<Int>): ResultWrapper<List<CharacterItem>> {
        return remoteDataSource.getCharacters(idList)
    }

    override suspend fun getEpisodeList(): ResultWrapper<Episode> {
        return remoteDataSource.getEpisodeList()
    }

    override suspend fun getEpisodeById(id: Int): ResultWrapper<EpisodeItem> {
        return remoteDataSource.getEpisodeById(id)
    }

    override suspend fun addToFavorites(character: CharacterItem) {
        localDataSource.setCharacter(character)
    }

    override suspend fun getFavorites(): List<CharacterItem> {
        "getFavorites".toLog()
        return localDataSource.getCharacters()
    }

    override suspend fun removeFromFavorites(character: CharacterItem) {
        localDataSource.removeCharacter(character)
    }
}