package com.dzhtv.rickandmortylibrary.data

import com.dzhtv.rickandmortylibrary.data.model.CharacterResponse
import com.dzhtv.rickandmortylibrary.data.model.CharacterFilterResponse
import com.dzhtv.rickandmortylibrary.data.model.EpisodeListResponse
import com.dzhtv.rickandmortylibrary.data.model.EpisodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character/")
    suspend fun getCharactersByFilter(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null
    ): Response<CharacterFilterResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacterByIdList(@Path("id") ids: Array<Int>): Response<List<CharacterResponse>>

    @GET("episode/")
    suspend fun getEpisodes(
        @Query("name") name: String? = null,
        @Query("episode") episodeCode: String? = null
    ): Response<EpisodeListResponse>

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Response<EpisodeResponse>
}