package com.dzhtv.rickandmortylibrary.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzhtv.rickandmortylibrary.presentation.base.BaseFragment
import com.dzhtv.rickandmortylibrary.databinding.FragmentCharacterBinding
import com.dzhtv.rickandmortylibrary.presentation.viewModel.CharacterViewModel

class CharacterDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentCharacterBinding
    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (this::binding.isInitialized) {
            characterViewModel = provideCharacterViewModel()
            binding.viewModel = characterViewModel
        }
    }
}