package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.data.model.CharacterEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RickAndMortyLocalDataSource(
    private val characterDao: CharacterDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getCharacters(): List<CharacterEntity> {
        return withContext(dispatcher) {
            characterDao.getCharacters()
        }
    }

    suspend fun findCharacterById(id: Int): CharacterEntity? {
        return withContext(dispatcher) {
            characterDao.findCharacter(id)
        }
    }
}