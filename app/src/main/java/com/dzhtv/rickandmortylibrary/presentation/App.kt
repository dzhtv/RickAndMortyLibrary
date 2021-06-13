package com.dzhtv.rickandmortylibrary.presentation


import android.app.Application
import com.dzhtv.rickandmortylibrary.di.ApplicationComponent
import com.dzhtv.rickandmortylibrary.di.DaggerApplicationComponent

class App : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().build()
    }
}