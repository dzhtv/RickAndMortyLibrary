package com.dzhtv.rickandmortylibrary.repository

import com.dzhtv.rickandmortylibrary.model.CharacterDto
import com.dzhtv.rickandmortylibrary.network.CharacterService

class CharacterRepository(private val client: CharacterService) : Repository {

    override suspend fun loadCharacters(page: Int?, name: String?): List<CharacterDto>? {
        val result = client.getCharacters()
        return result.body()?.results
    }
}