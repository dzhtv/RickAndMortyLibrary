package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.toLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RickAndMortyLocalDataSource(
    private val characterDao: CharacterDao,
    private val mapper: EntityMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getCharacters(): List<CharacterItem> {
        "getCharacters".toLog()
        return withContext(dispatcher) {
            characterDao.getCharacters().map { mapper.createCharacterItem(it) }
        }
    }

    suspend fun findCharacterById(id: Int): CharacterItem? {
        return withContext(dispatcher) {
            characterDao.findCharacter(id)?.let { mapper.createCharacterItem(it) }
        }
    }

    suspend fun setCharacter(item: CharacterItem) {
        withContext(dispatcher) {
            characterDao.insert(mapper.createEntity(item))
        }
    }

    suspend fun removeCharacter(item: CharacterItem) {
        withContext(dispatcher) {
            characterDao.delete(mapper.createEntity(item))
        }
    }
}