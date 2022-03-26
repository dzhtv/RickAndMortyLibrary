package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.data.model.CharacterEntity
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem

class EntityMapper {
    fun createEntity(value: CharacterItem): CharacterEntity {
        return CharacterEntity(
            characterId = value.id,
            character = value
        )
    }

    fun createCharacterItem(entity: CharacterEntity): CharacterItem {
        return CharacterItem(
            created = entity.character.created,
            episode = entity.character.episode,
            gender = entity.character.gender,
            id = entity.character.id,
            image = entity.character.image,
            location = entity.character.location,
            name = entity.character.name,
            origin = entity.character.origin,
            species = entity.character.species,
            status = entity.character.status,
            type = entity.character.type,
            url = entity.character.url,
            firstEpisodeItem = entity.character.firstEpisodeItem
        )
    }
}