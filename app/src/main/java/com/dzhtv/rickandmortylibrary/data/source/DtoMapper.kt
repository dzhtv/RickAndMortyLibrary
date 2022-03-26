package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.data.model.*
import com.dzhtv.rickandmortylibrary.domain.model.*

class DtoMapper {

    fun createCharacterFromResponse(response: CharacterResponse): CharacterItem {
        return CharacterItem(
            created = response.created,
            episode = response.episode,
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = createCharacterLocation(response.location),
            name = response.name,
            origin = createCharacterOrigin(response.origin),
            species = response.species,
            status = response.status,
            type = response.type,
            url = response.url,
            firstEpisodeItem = null
        )
    }

    fun createCharacterFilter(response: CharacterFilterResponse): Character {
        return Character(
            info = createRequestInfo(response.info),
            characters = response.results.map { createCharacterFromResponse(it) }
        )
    }

    fun createRequestInfo(response: InfoResponse): RequestInfo {
        return RequestInfo(
            count = response.count,
            pages = response.pages,
            prev = response.prev,
            next = response.next
        )
    }

    fun createCharacterLocation(response: CharacterLocationResponse): CharacterLocation {
        return CharacterLocation(
            name = response.name,
            url = response.url
        )
    }

    fun createCharacterOrigin(response: CharacterOriginResponse): CharacterOrigin {
        return CharacterOrigin(
            name = response.name,
            url = response.url
        )
    }

    fun createEpisodeItem(response: EpisodeResponse): EpisodeItem {
        return EpisodeItem(
            id = response.id,
            name = response.name,
            airDate = response.airDate,
            episode = response.episode,
            characters = response.characters,
            url = response.url,
            created = response.created
        )
    }

    fun createEpisode(info: RequestInfo, items: List<EpisodeItem>): Episode {
        return Episode(info, items)
    }
}