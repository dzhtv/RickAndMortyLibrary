package com.dzhtv.rickandmortylibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dzhtv.rickandmortylibrary.base.BaseFragment
import com.dzhtv.rickandmortylibrary.databinding.FragmentCharacterListBinding
import com.dzhtv.rickandmortylibrary.ui.main.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : BaseFragment() {

    private val characterViewModel: CharacterListViewModel by viewModels()
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        characterViewModel.fetchCharacters()

        binding.recyclerView.layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.recyclerView.adapter = characterViewModel.getCharacterAdapter()
        characterViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })
        characterViewModel.isLoadingProgress.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = it
        })
    }
}