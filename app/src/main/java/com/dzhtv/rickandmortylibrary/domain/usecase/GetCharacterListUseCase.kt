package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.Character
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    val repository: RickAndMortyRepository
) : BaseCoroutineUseCase<GetCharacterListUseCase.RequestValues, Character>() {

    data class RequestValues(
        val page: Int? = null
    ) : BaseCoroutineUseCase.RequestValues()

    override suspend fun run(params: RequestValues): Character {
        return repository.loadCharacters(params.page)
    }
}