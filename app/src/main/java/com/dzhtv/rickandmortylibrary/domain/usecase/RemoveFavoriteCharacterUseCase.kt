package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class RemoveFavoriteCharacterUseCase @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseCoroutineUseCase<RemoveFavoriteCharacterUseCase.RequestValues, Boolean>() {

    data class RequestValues(val character: CharacterItem) : BaseCoroutineUseCase.RequestValues()

    override suspend fun execute(params: RequestValues): Boolean {
        repository.removeFromFavorites(params.character)
        return true
    }
}