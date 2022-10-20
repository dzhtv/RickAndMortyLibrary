package com.dzhtv.rickandmortylibrary.di

import com.dzhtv.rickandmortylibrary.data.RickAndMortyApi
import com.dzhtv.rickandmortylibrary.data.source.*
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module(
    includes = [
        AppModule::class,
        NetworkModule::class
    ]
)
@InstallIn(ApplicationComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(
        characterDao: CharacterDao,
        episodeDao: EpisodeDao
    ): RickAndMortyLocalDataSource {
        return RickAndMortyLocalDataSource(characterDao, episodeDao, EntityMapper())
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(service: RickAndMortyApi): RickAndMortyRemoteDataSource {
        return RickAndMortyRemoteDataSource(
            service
        )
    }

    @Singleton
    @Provides
    fun provideRickAndMortyRepository(
        remoteDataSource: RickAndMortyRemoteDataSource,
        localDataSource: RickAndMortyLocalDataSource
    ): RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }
}