package com.dzhtv.rickandmortylibrary.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dzhtv.rickandmortylibrary.ui.character_list.CharacterListFragment
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.base.BaseViewModel
import com.dzhtv.rickandmortylibrary.ui.character_list.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, CharacterListFragment())
                .commit()
        }
    }

    fun getCharacterViewModel(): BaseViewModel {
        return characterViewModel
    }
}