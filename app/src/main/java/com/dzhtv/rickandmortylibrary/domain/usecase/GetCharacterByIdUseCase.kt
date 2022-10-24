package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    val repository: RickAndMortyRepository
): BaseCoroutineUseCase<GetCharacterByIdUseCase.RequestValues, CharacterItem>() {

    data class RequestValues(
        val id: Int
    ) : BaseCoroutineUseCase.RequestValues()

    override suspend fun run(params: RequestValues): CharacterItem {
        return repository.getCharacterById(params.id)
    }
}