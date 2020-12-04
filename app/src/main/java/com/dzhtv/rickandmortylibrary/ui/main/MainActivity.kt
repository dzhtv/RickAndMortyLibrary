package com.dzhtv.rickandmortylibrary.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dzhtv.rickandmortylibrary.ui.character_list.CharacterListFragment
import com.dzhtv.rickandmortylibrary.R
import dagger.hilt.android.AndroidEntryPoint

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