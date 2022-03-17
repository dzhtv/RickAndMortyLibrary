package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.domain.model.*
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RickAndMortyRemoteDataSource,
    private val localDataSource: RickAndMortyLocalDataSource
): RickAndMortyRepository {

    override suspend fun getCharactersByFilter(
        page: Int?,
        name: String?,
        status: CharacterStatus?,
        species: String?,
        type: String?,
        gender: CharacterGender?
    ): ResultWrapper<Character> {
        return remoteDataSource.getCharactersByFilter(page, name, status, species, type, gender)
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
}