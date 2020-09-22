package com.dzhtv.rickandmortylibrary.repository

import com.dzhtv.rickandmortylibrary.network.CharacterService

class CharacterRepository(private val client: CharacterService) : Repository {

    suspend fun fetchCharacterList(
        page: Int?,
        name: String?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

    }
}