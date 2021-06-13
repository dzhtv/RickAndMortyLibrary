package com.dzhtv.rickandmortylibrary.di

import com.dzhtv.rickandmortylibrary.presentation.adapter.CharacterGridAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule {

    @Provides
    fun provideCharacterAdapter(): CharacterGridAdapter {
        return CharacterGridAdapter()
    }
}