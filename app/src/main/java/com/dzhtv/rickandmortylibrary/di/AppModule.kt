package com.dzhtv.rickandmortylibrary.di

import com.dzhtv.rickandmortylibrary.network.CharacterService
import com.dzhtv.rickandmortylibrary.network.NetworkFactory
import com.dzhtv.rickandmortylibrary.network.repository.NetworkRepository
import com.dzhtv.rickandmortylibrary.network.repository.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

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
}