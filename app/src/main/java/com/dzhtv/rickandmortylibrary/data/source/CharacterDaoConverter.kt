package com.dzhtv.rickandmortylibrary.data.source

import androidx.room.TypeConverter
import com.dzhtv.rickandmortylibrary.data.model.CharacterLocationResponse
import com.dzhtv.rickandmortylibrary.data.model.CharacterOriginResponse
import com.dzhtv.rickandmortylibrary.data.model.CharacterResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CharacterDaoConverter {

    @TypeConverter
    fun fromList(response: List<String>): String {
        return Gson().toJson(response, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun toList(value: String?): List<String>? {
        return value?.let { Gson().fromJson(it, object : TypeToken<List<String>>() {}.type) }
    }

    @TypeConverter
    fun fromCharacterResponse(response: CharacterResponse): String {
        return Gson().toJson(response, object : TypeToken<CharacterResponse>() {}.type)
    }

    @TypeConverter
    fun toCharacterResponse(value: String?): CharacterResponse? {
        return value?.let { Gson().fromJson(it, CharacterResponse::class.java) }
    }

    @TypeConverter
    fun fromCharacterLocation(response: CharacterLocationResponse): String {
        return Gson().toJson(response, object : TypeToken<CharacterLocationResponse>() {}.type)
    }

    @TypeConverter
    fun toCharacterLocation(value: String?): CharacterLocationResponse? {
        return value?.let { Gson().fromJson(it, CharacterLocationResponse::class.java) }
    }

    @TypeConverter
    fun fromCharacterOrigin(response: CharacterOriginResponse): String {
        return Gson().toJson(response, object : TypeToken<CharacterOriginResponse>() {}.type)
    }

    @TypeConverter
    fun toCharacterOrigin(value: String?): CharacterOriginResponse? {
        return value?.let { Gson().fromJson(it, CharacterOriginResponse::class.java) }
    }
}