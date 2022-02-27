package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.Character
import com.dzhtv.rickandmortylibrary.domain.model.ResultWrapper
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetCharacterByFilterUseCase @Inject constructor(
    val repository: RickAndMortyRepository
) : BaseCoroutineUseCase<GetCharacterByFilterUseCase.RequestValues, ResultWrapper<Character>>() {

    data class RequestValues(
        val page: Int? = null,
        val name: String? = null
    ) : BaseCoroutineUseCase.InputValues()

    override suspend fun execute(params: RequestValues): ResultWrapper<Character> {
        return repository.getCharactersByFilter(
            page = params.page,
            name = params.name
        )
    }
}