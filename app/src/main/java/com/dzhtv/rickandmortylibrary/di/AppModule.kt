package com.dzhtv.rickandmortylibrary.di

import android.content.Context
import com.dzhtv.rickandmortylibrary.presentation.adapter.CharacterGridAdapter
import com.dzhtv.rickandmortylibrary.data.RemoteService
import com.dzhtv.rickandmortylibrary.data.NetworkFactory
import com.dzhtv.rickandmortylibrary.data.repository.RemoteRepository
import com.dzhtv.rickandmortylibrary.data.repository.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideCharacterService(): RemoteService {
        return NetworkFactory.RickAndMortyService.createService()
    }

    @Provides
    fun provideNetworkRepository(service: RemoteService): RemoteRepository {
        return RemoteRepositoryImpl(service)
    }

    @Provides
    fun provideCharacterAdapter(@ApplicationContext context: Context): CharacterGridAdapter {
        return CharacterGridAdapter(context)
    }
}