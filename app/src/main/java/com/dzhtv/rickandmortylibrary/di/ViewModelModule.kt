package com.dzhtv.rickandmortylibrary.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzhtv.rickandmortylibrary.presentation.viewModel.CharacterViewModel
import com.dzhtv.rickandmortylibrary.presentation.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharacterViewModel::class)
    internal abstract fun provideCharacterViewModel(viewModel: CharacterViewModel): ViewModel
}