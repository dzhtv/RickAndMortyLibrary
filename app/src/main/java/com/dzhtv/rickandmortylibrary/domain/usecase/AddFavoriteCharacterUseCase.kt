package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class AddFavoriteCharacterUseCase @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseCoroutineUseCase<AddFavoriteCharacterUseCase.RequestValues, Boolean>() {

    data class RequestValues(val character: CharacterItem) : BaseCoroutineUseCase.RequestValues()

    override suspend fun run(params: RequestValues): Boolean {
        repository.addToFavorites(params.character)
        return true
    }
}