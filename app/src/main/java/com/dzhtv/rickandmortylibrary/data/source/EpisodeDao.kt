package com.dzhtv.rickandmortylibrary.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dzhtv.rickandmortylibrary.data.model.EpisodeEntity

@Dao
interface EpisodeDao {

    @Query("select * from episodes where episodeId=:episodeId")
    suspend fun findEpisode(episodeId: Int): EpisodeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(entity: EpisodeEntity)

    @Delete
    suspend fun deleteEpisode(entity: EpisodeEntity)
}