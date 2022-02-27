package com.dzhtv.rickandmortylibrary.data.source

import androidx.room.TypeConverter
import com.dzhtv.rickandmortylibrary.data.model.CharacterLocationResponse
import com.dzhtv.rickandmortylibrary.data.model.CharacterOriginResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CharacterDaoConverter {

    @TypeConverter
    fun fromCharacterLocationResponse(response: CharacterLocationResponse): String {
        return Gson().toJson(response, object : TypeToken<CharacterLocationResponse>() {}.type)
    }

    @TypeConverter
    fun toCharacterLocationResponse(value: String?): CharacterLocationResponse? {
        return value?.let { Gson().fromJson(value, CharacterLocationResponse::class.java) }
    }

    @TypeConverter
    fun fromCharacterOriginResponse(response: CharacterOriginResponse): String {
        return Gson().toJson(response, object : TypeToken<CharacterOriginResponse>() {}.type)
    }

    @TypeConverter
    fun toCharacterOriginResponse(value: String?): CharacterOriginResponse? {
        return value?.let { Gson().fromJson(value, CharacterOriginResponse::class.java) }
    }

    @TypeConverter
    fun fromEpisodeListResponse(response: List<String>): String {
        return Gson().toJson(response, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun toEpisodeListResponse(value: String?): List<String>? {
        return value?.let { Gson().fromJson(value, object : TypeToken<List<String>>() {}.type) }
    }
}