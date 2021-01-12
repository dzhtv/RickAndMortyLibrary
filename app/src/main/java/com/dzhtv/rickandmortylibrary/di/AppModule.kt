package com.dzhtv.rickandmortylibrary.di

import android.content.Context
import com.dzhtv.rickandmortylibrary.presentation.adapter.CharacterGridAdapter
import com.dzhtv.rickandmortylibrary.data.CharacterService
import com.dzhtv.rickandmortylibrary.data.NetworkFactory
import com.dzhtv.rickandmortylibrary.data.repository.NetworkRepository
import com.dzhtv.rickandmortylibrary.data.repository.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideCharacterService(): CharacterService {
        return NetworkFactory.RickAndMortyService.createService()
    }

    @Provides
    fun provideNetworkRepository(service: CharacterService): NetworkRepository {
        return NetworkRepositoryImpl(service)
    }

    @Provides
    fun provideCharacterAdapter(@ApplicationContext context: Context): CharacterGridAdapter {
        return CharacterGridAdapter(context)
    }
}