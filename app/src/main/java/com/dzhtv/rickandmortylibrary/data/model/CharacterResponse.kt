package com.dzhtv.rickandmortylibrary.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dzhtv.rickandmortylibrary.data.source.CharacterDaoConverter

@Entity(tableName = "characters")
@TypeConverters(CharacterDaoConverter::class)
data class CharacterResponse(
    @PrimaryKey val id: Int,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val location: CharacterLocationResponse,
    val name: String,
    val origin: CharacterOriginResponse,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)