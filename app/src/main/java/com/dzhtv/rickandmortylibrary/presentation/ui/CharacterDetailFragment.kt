package com.dzhtv.rickandmortylibrary.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzhtv.rickandmortylibrary.presentation.base.BaseFragment
import com.dzhtv.rickandmortylibrary.databinding.FragmentCharacterBinding
import com.dzhtv.rickandmortylibrary.presentation.viewModel.CharacterViewModel

class CharacterDetailFragment : BaseFragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        characterViewModel = provideCharacterViewModel()
        binding.viewModel = characterViewModel
        return binding.root
    }
}