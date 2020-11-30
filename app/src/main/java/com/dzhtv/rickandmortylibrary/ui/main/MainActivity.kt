package com.dzhtv.rickandmortylibrary.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.dzhtv.rickandmortylibrary.CharacterListFragment
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.network.NetworkFactory
import com.dzhtv.rickandmortylibrary.network.repository.NetworkRepositoryImpl
import com.dzhtv.rickandmortylibrary.network.repository.NetworkRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, CharacterListFragment())
                .commit()
        }
    }
}