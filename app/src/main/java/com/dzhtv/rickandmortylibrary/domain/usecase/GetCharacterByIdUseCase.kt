package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.model.ResultWrapper
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRemoteRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    val repository: RickAndMortyRemoteRepository
): BaseCoroutineUseCase<GetCharacterByIdUseCase.RequestValues, ResultWrapper<CharacterItem>>() {

    data class RequestValues(
        val id: Int
    ) : BaseCoroutineUseCase.InputValues()

    override suspend fun execute(params: RequestValues): ResultWrapper<CharacterItem> {
        return repository.getCharacterById(params.id)
    }
}