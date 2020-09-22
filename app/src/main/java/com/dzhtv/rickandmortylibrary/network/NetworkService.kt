package com.dzhtv.rickandmortylibrary.network

import com.dzhtv.rickandmortylibrary.model.BaseResponse
import com.dzhtv.rickandmortylibrary.model.CharacterDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null
    ): Call<BaseResponse<CharacterDto>>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int)

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") idList: String)
}