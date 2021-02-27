package com.dzhtv.rickandmortylibrary.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dzhtv.rickandmortylibrary.databinding.FragmentEpisodeDetailBinding
import com.dzhtv.rickandmortylibrary.presentation.base.BaseFragment

class EpisodeDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentEpisodeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (this::binding.isInitialized) {
            binding.viewModel = provideCharacterViewModel()
        }
        initUI()
    }

    private fun initUI() {
        binding.recyclerView.layoutManager = GridLayoutManager(appContext(), 4)
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}