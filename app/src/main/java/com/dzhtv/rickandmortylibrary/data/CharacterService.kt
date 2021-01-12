package com.dzhtv.rickandmortylibrary.data

import com.dzhtv.rickandmortylibrary.data.model.BaseResponse
import com.dzhtv.rickandmortylibrary.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null
    ): Response<BaseResponse<Character>>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Character>

    @GET("character/{id}")
    suspend fun getCharacterByIdList(@Path("id") idList: String): Response<List<Character>?>

}