package com.dzhtv.rickandmortylibrary.di

import com.dzhtv.rickandmortylibrary.presentation.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ActivityModule::class, ViewModelModule::class, NetworkModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}