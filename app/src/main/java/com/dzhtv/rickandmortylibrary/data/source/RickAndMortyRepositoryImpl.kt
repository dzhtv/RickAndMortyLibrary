package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.domain.model.*
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RickAndMortyRemoteDataSource,
    private val localDataSource: RickAndMortyLocalDataSource
): RickAndMortyRepository {

    override suspend fun loadCharacters(page: Int?): ResultWrapper<Character> {
        return remoteDataSource.getCharactersByFilter(page)
    }

    override suspend fun getCharacterById(id: Int): ResultWrapper<CharacterItem> {
        return remoteDataSource.getCharacterById(id)
    }

    override suspend fun getCharacters(idList: Array<Int>): ResultWrapper<List<CharacterItem>> {
        return remoteDataSource.getCharacters(idList)
    }

    override suspend fun getEpisodeList(): ResultWrapper<Episode> {
        return remoteDataSource.getEpisodeList()
    }

    override suspend fun getEpisodeById(id: Int): ResultWrapper<EpisodeItem> {
        return remoteDataSource.getEpisodeById(id)
    }

    override suspend fun addToFavorites(character: CharacterItem): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorites(characterId: Int): Boolean {
        TODO("Not yet implemented")
    }
}