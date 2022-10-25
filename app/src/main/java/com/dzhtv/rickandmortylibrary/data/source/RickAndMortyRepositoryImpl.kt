package com.dzhtv.rickandmortylibrary.data.source

import com.dzhtv.rickandmortylibrary.data.model.CharacterFilterResponse
import com.dzhtv.rickandmortylibrary.data.model.CharacterResponse
import com.dzhtv.rickandmortylibrary.domain.model.Character
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.model.Episode
import com.dzhtv.rickandmortylibrary.domain.model.EpisodeItem
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import com.dzhtv.rickandmortylibrary.toLog
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RickAndMortyRemoteDataSource,
    private val localDataSource: RickAndMortyLocalDataSource
) : RickAndMortyRepository, BaseNetworkHandler() {

    override suspend fun loadCharacters(page: Int?): Character {
        val filterResponse = parseResult(
            remoteDataSource.getCharactersByFilter(page)
        )
        val characters = buildCharacters(filterResponse)
        return DtoMapper.createCharacterFilter(filterResponse, characters)
    }

    override suspend fun getCharacterById(id: Int): CharacterItem {
        val response = remoteDataSource.getCharacterById(id)
        return parseResult(response) {
            DtoMapper.createCharacterFromResponse(it)
        }
    }

    override suspend fun getCharacters(idList: List<Int>): List<CharacterItem> {
        val response = remoteDataSource.getCharacters(idList)
        return parseResult(response) { items ->
            items.map { DtoMapper.createCharacterFromResponse(it) }
        }
    }

    override suspend fun getEpisodeList(): Episode {
        val response = remoteDataSource.getEpisodeList()
        return parseResult(response) { episodeListResponse ->
            DtoMapper.createEpisode(
                DtoMapper.createRequestInfo(episodeListResponse.info),
                episodeListResponse.results.map {
                    DtoMapper.createEpisodeItem(it)
                }
            )
        }
    }

    override suspend fun getEpisodeById(id: Int): EpisodeItem {
        val response = remoteDataSource.getEpisodeById(id)
        return parseResult(response) {
            DtoMapper.createEpisodeItem(it)
        }
    }

    override suspend fun addToFavorites(character: CharacterItem) {
        localDataSource.setCharacter(DtoMapper.createCharacterResponse(character))
    }

    override suspend fun getFavorites(): List<CharacterItem> {
        "getFavorites".toLog()
        val response = localDataSource.getCharacters()
        return response.map { DtoMapper.createCharacterFromResponse(it) }
    }

    override suspend fun findFavoriteCharacter(id: Int): CharacterItem? {
        val response = localDataSource.findCharacterById(id)
        return response?.let { DtoMapper.createCharacterFromResponse(it) }
    }

    override suspend fun removeFromFavorites(character: CharacterItem) {
        localDataSource.removeCharacter(DtoMapper.createCharacterResponse(character))
    }

    private suspend fun buildCharacters(response: CharacterFilterResponse): List<CharacterItem> {
        val characters = ArrayList<CharacterItem>()
        response.results.forEach { item ->
            val savedItem = localDataSource.findCharacterById(item.id)
            val firstEpisode = remoteDataSource.getEpisodeById(
                getFirstAppearanceCharacter(item)
            )
            characters.add(
                DtoMapper.createCharacterFromResponse(
                    item,
                    hasInFavorites = savedItem != null,
                    firstEpisode = parseResult(firstEpisode)
                )
            )
        }
        return characters
    }

    private fun getFirstAppearanceCharacter(value: CharacterResponse): Int {
        return value.episode.first().substringAfterLast("episode/").toInt()
    }
}