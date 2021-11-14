package com.dzhtv.rickandmortylibrary.di

import com.dzhtv.rickandmortylibrary.BuildConfig
import com.dzhtv.rickandmortylibrary.presentation.adapter.CharacterGridAdapter
import com.dzhtv.rickandmortylibrary.data.RickAndMortyApi
import com.dzhtv.rickandmortylibrary.data.source.DtoMapper
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRemoteRepository
import com.dzhtv.rickandmortylibrary.data.source.RickAndMortyRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module(includes = [GlideUnsafeModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideCharacterService(client: OkHttpClient): RickAndMortyApi {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build().create(RickAndMortyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(service: RickAndMortyApi): RickAndMortyRemoteRepository {
        return RickAndMortyRemoteRepositoryImpl(client = service, mapper = DtoMapper())
    }

    @Singleton
    @Provides
    fun provideCharacterAdapter(): CharacterGridAdapter {
        return CharacterGridAdapter()
    }
}