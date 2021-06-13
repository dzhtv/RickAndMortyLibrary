package com.dzhtv.rickandmortylibrary.data

import com.dzhtv.rickandmortylibrary.data.model.Character
import com.dzhtv.rickandmortylibrary.data.model.CharacterResponse
import com.dzhtv.rickandmortylibrary.data.model.Episode
import com.dzhtv.rickandmortylibrary.data.model.EpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character

    @GET("character/{id}")
    suspend fun getCharacterByIdList(@Path("id") idList: String): List<Character>

    @GET("episode/")
    suspend fun getEpisodes(
        @Query("name") name: String? = null,
        @Query("episode") episodeCode: String? = null
    ): EpisodeResponse

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Episode
}