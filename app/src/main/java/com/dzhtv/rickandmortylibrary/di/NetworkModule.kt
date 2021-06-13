package com.dzhtv.rickandmortylibrary.di


import com.dzhtv.rickandmortylibrary.data.RickAndMortyApi
import com.dzhtv.rickandmortylibrary.data.NetworkFactory
import com.dzhtv.rickandmortylibrary.data.repository.RemoteRepository
import com.dzhtv.rickandmortylibrary.data.repository.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideCharacterService(): RickAndMortyApi {
        return NetworkFactory.RickAndMortyService.createService()
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(service: RickAndMortyApi): RemoteRepository {
        return RemoteRepositoryImpl(service)
    }
}