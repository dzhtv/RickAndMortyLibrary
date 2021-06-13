package com.dzhtv.rickandmortylibrary.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface ApplicationComponent {

}