package com.dzhtv.rickandmortylibrary.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.presentation.adapter.OnItemClickListener
import com.dzhtv.rickandmortylibrary.presentation.adapter.RecyclerItemClickListener
import com.dzhtv.rickandmortylibrary.databinding.FragmentCharacterListBinding
import com.dzhtv.rickandmortylibrary.presentation.adapter.CharacterGridAdapter
import com.dzhtv.rickandmortylibrary.presentation.showSnackbar
import com.dzhtv.rickandmortylibrary.presentation.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : BaseFragment() {

    @Inject
    lateinit var fragmentAdapter: CharacterGridAdapter

    private val characterViewModel: CharacterViewModel by activityViewModels()
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeViewModel()
        characterViewModel.fetchCharacters()
    }

    private fun initUI() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = fragmentAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        characterViewModel.onScrolledRecyclerToEnd()
                    }
                }
            })
            addOnItemTouchListener(
                RecyclerItemClickListener(
                    context,
                    recyclerView = this
                ) { view, position ->
                    characterViewModel.clickOnPosition(position)
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main, CharacterDetailFragment())
                        .addToBackStack(null)
                        .commit()
                }
            )
        }
    }

    private fun observeViewModel() {
        characterViewModel.characters.observe(viewLifecycleOwner) { characters ->
            fragmentAdapter.refreshItems(characters)
        }

        characterViewModel.errorMessage.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { text ->
                binding.recyclerView.showSnackbar(text)
            }
        }

        characterViewModel.isLoadingProgress.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                binding.progressBar.visibility = it
            }
        }

        characterViewModel.scrollDown.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                binding.recyclerView.layoutManager?.let {
                    it.scrollToPosition((it as LinearLayoutManager).findFirstVisibleItemPosition() + 9)
                }
            }
        }
    }
}