package com.dzhtv.rickandmortylibrary.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.databinding.FragmentCharacterDetailBinding
import com.dzhtv.rickandmortylibrary.presentation.activity.MainActivity
import com.dzhtv.rickandmortylibrary.presentation.viewmodel.CharacterViewModel
import com.dzhtv.rickandmortylibrary.presentation.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentCharacterDetailBinding
    private val characterViewModel: CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (this::binding.isInitialized) {
            binding.viewModel = characterViewModel
            initUI()
        }
    }

    private fun initUI() {
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        characterViewModel.characterItem.observe(viewLifecycleOwner) {
            it.firstEpisodeItem?.let { episode ->
                binding.episodeField.apply {
                    setTitle(context.getString(R.string.first_appearance))
                    setHeaderText(episode.name)
                    setSubheader(episode.episode)
                    setPicture(isVisible = true)
                    visible()
                    setOnClickListener {
                        if (requireActivity() is MainActivity) {
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(R.id.container_main, EpisodeDetailFragment())
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                }
            }
        }
    }
}