package com.dzhtv.rickandmortylibrary.di

import android.content.Context
import com.dzhtv.rickandmortylibrary.data.RickAndMortyApi
import com.dzhtv.rickandmortylibrary.data.source.*
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import com.dzhtv.rickandmortylibrary.presentation.adapter.CharacterGridAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideCharacterDao(database: AppDatabase): CharacterDao {
        return database.getCharacterDao()
    }

    @Singleton
    @Provides
    fun provideCharacterAdapter(): CharacterGridAdapter {
        return CharacterGridAdapter()
    }
}