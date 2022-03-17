package com.dzhtv.rickandmortylibrary.data.source

import androidx.room.*
import com.dzhtv.rickandmortylibrary.data.model.CharacterResponse

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): List<CharacterResponse>

    @Query("SELECT * FROM characters WHERE id =:characterId")
    suspend fun findCharacter(characterId: Int): CharacterResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(item: CharacterResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<CharacterResponse>)

    @Delete
    suspend fun delete(character: CharacterResponse)
}