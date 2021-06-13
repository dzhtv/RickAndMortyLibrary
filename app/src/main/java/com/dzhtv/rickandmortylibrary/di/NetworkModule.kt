package com.dzhtv.rickandmortylibrary.di

import com.dzhtv.rickandmortylibrary.BuildConfig
import com.dzhtv.rickandmortylibrary.data.RickAndMortyApi
import com.dzhtv.rickandmortylibrary.data.repository.RemoteRepository
import com.dzhtv.rickandmortylibrary.data.repository.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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
    fun provideNetworkRepository(service: RickAndMortyApi): RemoteRepository {
        return RemoteRepositoryImpl(service)
    }
}