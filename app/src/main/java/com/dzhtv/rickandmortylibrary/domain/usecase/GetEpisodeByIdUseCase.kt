package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.EpisodeItem
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    val repository: RickAndMortyRepository
) : BaseCoroutineUseCase<GetEpisodeByIdUseCase.RequestValues, EpisodeItem>() {

    data class RequestValues(
        val episodeId: Int
    ) : BaseCoroutineUseCase.RequestValues()

    override suspend fun run(params: RequestValues): EpisodeItem {
        return repository.getEpisodeById(params.episodeId)
    }
}