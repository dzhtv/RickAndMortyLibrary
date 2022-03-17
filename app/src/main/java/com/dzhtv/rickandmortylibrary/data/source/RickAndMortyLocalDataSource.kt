package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.data.model.CharacterResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RickAndMortyLocalDataSource(
    private val characterDao: CharacterDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getCharacters(): List<CharacterResponse>? {
        return withContext(dispatcher) {
            characterDao.getCharacters()
        }
    }

    suspend fun findCharacterById(id: Int): CharacterResponse? {
        return withContext(dispatcher) {
            characterDao.findCharacter(id)
        }
    }
}