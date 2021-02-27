package com.dzhtv.rickandmortylibrary.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.databinding.FragmentCharacterDetailBinding
import com.dzhtv.rickandmortylibrary.presentation.base.BaseActivity
import com.dzhtv.rickandmortylibrary.presentation.base.BaseFragment
import com.dzhtv.rickandmortylibrary.presentation.log
import com.dzhtv.rickandmortylibrary.presentation.viewModel.CharacterViewModel
import com.dzhtv.rickandmortylibrary.presentation.visible

class CharacterDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel = provideCharacterViewModel()
        if (this::binding.isInitialized) {
            binding.viewModel = characterViewModel
            initUI()
        }
    }

    private fun initUI() {
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        characterViewModel.characterEpisodeStart.observe(viewLifecycleOwner, Observer {
            it?.let { episode ->
                binding.episodeField?.let { field ->
                    field.setTitle(appContext().getString(R.string.first_appearance))
                    field.setHeaderText(episode.name)
                    field.setSubheader(episode.episode)
                    field.setPicture(isVisible = true)
                    field.visible()
                    field.setOnClickListener {
                        if (requireActivity() is MainActivity) {
                            (requireActivity() as MainActivity).replaceFragment(EpisodeDetailFragment())
                        }
                    }
                }
            }
        })
    }

}