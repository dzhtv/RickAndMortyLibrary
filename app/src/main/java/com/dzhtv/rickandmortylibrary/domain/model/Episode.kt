package com.dzhtv.rickandmortylibrary.domain.model

data class Episode(
    val info: RequestInfo,
    val episodes: List<EpisodeItem>
)
