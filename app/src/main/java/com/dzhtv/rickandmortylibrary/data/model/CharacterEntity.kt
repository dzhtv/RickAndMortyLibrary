package com.dzhtv.rickandmortylibrary.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dzhtv.rickandmortylibrary.data.source.CharacterDaoConverter
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem

@Entity(tableName = "characters")
@TypeConverters(CharacterDaoConverter::class)
data class CharacterEntity(
    @PrimaryKey val characterId: Int,
    val character: CharacterItem
)
