package com.dzhtv.rickandmortylibrary.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.presentation.adapter.OnItemClickListener
import com.dzhtv.rickandmortylibrary.presentation.adapter.RecyclerItemClickListener
import com.dzhtv.rickandmortylibrary.presentation.base.BaseFragment
import com.dzhtv.rickandmortylibrary.databinding.FragmentCharacterListBinding
import com.dzhtv.rickandmortylibrary.presentation.showSnackbar
import com.dzhtv.rickandmortylibrary.presentation.viewModel.CharacterViewModel

class CharacterListFragment : BaseFragment() {

    private lateinit var characterViewModel: CharacterViewModel
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        characterViewModel = provideCharacterViewModel()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        characterViewModel.fetchCharacters()

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = characterViewModel.getCharacterAdapter()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        characterViewModel.onScrolledRecyclerToEnd()
                    }
                }
            })
            addOnItemTouchListener(RecyclerItemClickListener(requireActivity(), this, object : OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    characterViewModel.clickOnPosition(position)
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main, CharacterDetailFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }))
        }
        characterViewModel.errorMessage.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { text ->
                binding.recyclerView.showSnackbar(text)
            }
        })
        characterViewModel.isLoadingProgress.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = it
        })
        characterViewModel.scrollDown.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let {
                binding.recyclerView.layoutManager?.let {
                    it.scrollToPosition((it as LinearLayoutManager).findFirstVisibleItemPosition() + 9)
                }
            }
        })
    }
}