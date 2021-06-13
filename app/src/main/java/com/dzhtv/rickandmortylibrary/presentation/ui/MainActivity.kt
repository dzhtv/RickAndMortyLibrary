package com.dzhtv.rickandmortylibrary.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.presentation.base.BaseActivity
import com.dzhtv.rickandmortylibrary.presentation.base.BaseFragment
import com.dzhtv.rickandmortylibrary.presentation.base.BaseViewModel
import com.dzhtv.rickandmortylibrary.presentation.viewModel.CharacterViewModel

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