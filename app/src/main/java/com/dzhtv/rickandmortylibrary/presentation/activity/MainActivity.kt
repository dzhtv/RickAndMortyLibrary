package com.dzhtv.rickandmortylibrary.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.presentation.viewmodel.BaseViewModel
import com.dzhtv.rickandmortylibrary.presentation.fragment.CharacterListFragment
import com.dzhtv.rickandmortylibrary.presentation.viewmodel.CharacterViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showFragment(CharacterListFragment())
        }
    }

    fun getCharacterViewModel(): BaseViewModel {
        return characterViewModel
    }
}