package com.dzhtv.rickandmortylibrary.presentation.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.databinding.ActivityMainBinding
import com.dzhtv.rickandmortylibrary.presentation.fragment.CharacterListFragment
import com.dzhtv.rickandmortylibrary.presentation.fragment.FavoritesListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = binding.bottomNavigationView
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            return@setOnNavigationItemSelectedListener when (menuItem.itemId) {
                R.id.network_list -> {
                    openCharactersFragment()
                    true
                }
                R.id.saved_list -> {
                    openFavoritesFragment()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        refreshNavigation()
    }

    override fun getContainerView(): View = binding.containerMain

    override fun getFullscreenView(): View = binding.fullscreenContainer

    private fun refreshNavigation() {
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            showFragment(CharacterListFragment.create())
        }
    }

    private fun openCharactersFragment() {
        showFragment(CharacterListFragment.create())
    }

    private fun openFavoritesFragment() {
        showFragment(FavoritesListFragment.create())
    }
}