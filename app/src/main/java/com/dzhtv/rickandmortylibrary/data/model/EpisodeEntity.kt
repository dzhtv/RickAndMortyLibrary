package com.dzhtv.rickandmortylibrary.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dzhtv.rickandmortylibrary.data.source.EpisodeDaoConverter

@Entity(tableName = "episodes")
@TypeConverters(EpisodeDaoConverter::class)
data class EpisodeEntity(
    @PrimaryKey val episodeId: Int,
    val episodeItem: EpisodeResponse
)