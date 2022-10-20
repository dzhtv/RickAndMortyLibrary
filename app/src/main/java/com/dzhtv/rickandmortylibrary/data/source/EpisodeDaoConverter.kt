package com.dzhtv.rickandmortylibrary.data.source

import androidx.room.TypeConverter
import com.dzhtv.rickandmortylibrary.data.model.EpisodeResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object EpisodeDaoConverter {

    @TypeConverter
    fun fromList(response: List<String>): String {
        return Gson().toJson(response, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun toList(value: String?): List<String>? {
        return value?.let { Gson().fromJson(it, object : TypeToken<List<String>>() {}.type) }
    }

    @TypeConverter
    fun fromEpisodeResponse(response: EpisodeResponse): String {
        return Gson().toJson(response, object : TypeToken<EpisodeResponse>() {}.type)
    }

    @TypeConverter
    fun toEpisodeResponse(value: String?): EpisodeResponse? {
        return value?.let { Gson().fromJson(it, EpisodeResponse::class.java) }
    }
}