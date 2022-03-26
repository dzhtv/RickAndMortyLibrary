package com.dzhtv.rickandmortylibrary.data.source

import androidx.room.TypeConverter
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.model.CharacterLocation
import com.dzhtv.rickandmortylibrary.domain.model.CharacterOrigin
import com.dzhtv.rickandmortylibrary.domain.model.EpisodeItem
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
    fun fromCharacterItem(response: CharacterItem): String {
        return Gson().toJson(response, object : TypeToken<CharacterItem>() {}.type)
    }

    @TypeConverter
    fun toCharacterItem(value: String?): CharacterItem? {
        return value?.let { Gson().fromJson(it, CharacterItem::class.java) }
    }

    @TypeConverter
    fun fromEpisodeItem(response: EpisodeItem): String? {
        return Gson().toJson(response, object : TypeToken<EpisodeItem>() {}.type)
    }

    @TypeConverter
    fun toEpisodeItem(value: String?): EpisodeItem? {
        return value?.let { Gson().fromJson(it, EpisodeItem::class.java) }
    }

    @TypeConverter
    fun fromCharacterLocation(response: CharacterLocation): String {
        return Gson().toJson(response, object : TypeToken<CharacterLocation>() {}.type)
    }

    @TypeConverter
    fun toCharacterLocation(value: String?): CharacterLocation? {
        return value?.let { Gson().fromJson(it, CharacterLocation::class.java) }
    }

    @TypeConverter
    fun fromCharacterOrigin(response: CharacterOrigin): String? {
        return Gson().toJson(response, object : TypeToken<CharacterOrigin>() {}.type)
    }

    @TypeConverter
    fun toCharacterOrigin(value: String?): CharacterOrigin? {
        return value?.let { Gson().fromJson(it, CharacterOrigin::class.java) }
    }
}