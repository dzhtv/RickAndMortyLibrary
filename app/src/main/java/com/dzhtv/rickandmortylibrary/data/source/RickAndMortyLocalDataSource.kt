package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.data.model.CharacterResponse
import com.dzhtv.rickandmortylibrary.data.model.EpisodeResponse
import com.dzhtv.rickandmortylibrary.toLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RickAndMortyLocalDataSource(
    private val characterDao: CharacterDao,
    private val episodeDao: EpisodeDao,
    private val mapper: EntityMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getCharacters(): List<CharacterResponse> {
        "getCharacters".toLog()
        return withContext(dispatcher) {
            characterDao.getCharacters().map { mapper.createCharacterResponse(it) }
        }
    }

    suspend fun findCharacterById(id: Int): CharacterResponse? {
        return withContext(dispatcher) {
            characterDao.findCharacter(id)?.let { mapper.createCharacterResponse(it) }
        }
    }

    suspend fun setCharacter(item: CharacterResponse) {
        withContext(dispatcher) {
            characterDao.insert(mapper.createCharacterEntity(item))
        }
    }

    suspend fun removeCharacter(item: CharacterResponse) {
        withContext(dispatcher) {
            characterDao.delete(mapper.createCharacterEntity(item))
        }
    }

    suspend fun findEpisodeById(id: Int): EpisodeResponse? {
        return withContext(dispatcher) {
            episodeDao.findEpisode(id)?.let { mapper.createEpisodeResponse(it) }
        }
    }

    suspend fun setEpisode(item: EpisodeResponse) {
        withContext(dispatcher) {
            episodeDao.insertEpisode(
                mapper.createEpisodeEntity(item)
            )
        }
    }
}