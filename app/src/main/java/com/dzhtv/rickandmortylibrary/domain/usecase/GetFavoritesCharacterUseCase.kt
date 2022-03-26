package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesCharacterUseCase @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseFlowUseCase<BaseFlowUseCase.EmptyValues, List<CharacterItem>>() {

    override suspend fun execute(params: EmptyValues): Flow<List<CharacterItem>> {
        return flow {
            repository.getFavorites()
        }
    }
}