package com.dzhtv.rickandmortylibrary.repository

import com.dzhtv.rickandmortylibrary.model.CharacterDto

interface Repository {

   suspend fun loadCharacters(page: Int?, name: String?): List<CharacterDto>?
}