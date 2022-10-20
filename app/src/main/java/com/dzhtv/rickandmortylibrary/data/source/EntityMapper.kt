package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.data.model.CharacterEntity
import com.dzhtv.rickandmortylibrary.data.model.CharacterResponse
import com.dzhtv.rickandmortylibrary.data.model.EpisodeEntity
import com.dzhtv.rickandmortylibrary.data.model.EpisodeResponse

class EntityMapper {
    fun createCharacterEntity(value: CharacterResponse): CharacterEntity {
        return CharacterEntity(
            characterId = value.id,
            characterResponse = value
        )
    }

    fun createCharacterResponse(entity: CharacterEntity): CharacterResponse {
        return entity.characterResponse
    }

    fun createEpisodeEntity(response: EpisodeResponse): EpisodeEntity {
        return EpisodeEntity(
            episodeId = response.id,
            episodeItem = response
        )
    }

    fun createEpisodeResponse(entity: EpisodeEntity): EpisodeResponse {
        return entity.episodeItem
    }
}