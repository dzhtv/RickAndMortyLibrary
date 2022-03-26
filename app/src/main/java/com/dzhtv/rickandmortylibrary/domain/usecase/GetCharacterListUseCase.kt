package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.Character
import com.dzhtv.rickandmortylibrary.domain.model.ResultWrapper
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    val repository: RickAndMortyRepository
) : BaseCoroutineUseCase<GetCharacterListUseCase.RequestValues, ResultWrapper<Character>>() {

    data class RequestValues(
        val page: Int? = null
    ) : BaseCoroutineUseCase.RequestValues()

    override suspend fun execute(params: RequestValues): ResultWrapper<Character> {
        return repository.loadCharacters(params.page)
    }
}