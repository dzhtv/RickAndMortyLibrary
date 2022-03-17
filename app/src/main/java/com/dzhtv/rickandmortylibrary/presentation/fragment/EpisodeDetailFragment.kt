package com.dzhtv.rickandmortylibrary.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.dzhtv.rickandmortylibrary.databinding.FragmentEpisodeDetailBinding
import com.dzhtv.rickandmortylibrary.presentation.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentEpisodeDetailBinding
    private val viewModel: CharacterViewModel by activityViewModels()

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
            binding.viewModel = viewModel
        }
        initUI()
    }

    private fun initUI() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 4)
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}