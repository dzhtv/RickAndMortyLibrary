package com.dzhtv.rickandmortylibrary.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dzhtv.rickandmortylibrary.data.model.CharacterEntity
import com.dzhtv.rickandmortylibrary.data.model.EpisodeEntity

@Database(
    entities = [CharacterEntity::class, EpisodeEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(CharacterDaoConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao
    abstract fun getEpisodeDao(): EpisodeDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "RickAndMorty.db").build()
        }
    }
}