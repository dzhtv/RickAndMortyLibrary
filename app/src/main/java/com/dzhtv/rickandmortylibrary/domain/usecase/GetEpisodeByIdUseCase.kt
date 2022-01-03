package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.EpisodeItem
import com.dzhtv.rickandmortylibrary.domain.model.ResultWrapper
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRemoteRepository
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    val repository: RickAndMortyRemoteRepository
) : BaseCoroutineUseCase<GetEpisodeByIdUseCase.RequestValues, ResultWrapper<EpisodeItem>>() {

    data class RequestValues(
        val episodeId: Int
    ) : BaseCoroutineUseCase.InputValues()

    override suspend fun execute(params: RequestValues): ResultWrapper<EpisodeItem> {
        return repository.getEpisodeById(params.episodeId)
    }
}