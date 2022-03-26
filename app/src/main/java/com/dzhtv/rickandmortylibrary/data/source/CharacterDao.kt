package com.dzhtv.rickandmortylibrary.data.source

import androidx.room.*
import com.dzhtv.rickandmortylibrary.data.model.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE characterId =:characterId")
    suspend fun findCharacter(characterId: Int): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<CharacterEntity>)

    @Delete
    suspend fun delete(character: CharacterEntity)
}