package com.dzhtv.rickandmortylibrary.presentation.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.di.ActivityComponent
import com.dzhtv.rickandmortylibrary.di.DaggerActivityComponent
import com.dzhtv.rickandmortylibrary.presentation.base.BaseActivity
import com.dzhtv.rickandmortylibrary.presentation.base.BaseViewModel
import com.dzhtv.rickandmortylibrary.presentation.viewModel.CharacterViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

//   private val characterViewModel: CharacterViewModel by viewModels()
    lateinit var activityComponent: ActivityComponent
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var characterViewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent = DaggerActivityComponent.builder().build()
        activityComponent.inject(this)

        characterViewModel = ViewModelProvider(this, viewModelFactory).get(CharacterViewModel::class.java)

        if (savedInstanceState == null) {
            showFragment(CharacterListFragment())
        }
    }

    fun getCharacterViewModel(): BaseViewModel {
        return characterViewModel
    }
}