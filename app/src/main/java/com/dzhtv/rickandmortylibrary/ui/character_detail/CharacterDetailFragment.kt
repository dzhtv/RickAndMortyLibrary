package com.dzhtv.rickandmortylibrary.ui.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.dzhtv.rickandmortylibrary.base.BaseFragment
import com.dzhtv.rickandmortylibrary.databinding.FragmentCharacterBinding
import com.dzhtv.rickandmortylibrary.log
import com.dzhtv.rickandmortylibrary.network.GlideApp
import com.dzhtv.rickandmortylibrary.ui.character_list.CharacterViewModel
import com.dzhtv.rickandmortylibrary.ui.main.MainActivity

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

    override fun onStart() {
        super.onStart()
        characterViewModel.characterImageUrl.observe(viewLifecycleOwner, Observer {
            GlideApp.with(requireActivity())
                .load(it)
                .centerInside()
                .into(binding.image)
        })
    }
}