package com.dzhtv.rickandmortylibrary.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dzhtv.rickandmortylibrary.TAG
import com.dzhtv.rickandmortylibrary.databinding.FragmentCharacterListBinding
import com.dzhtv.rickandmortylibrary.presentation.adapter.CharacterGridAdapter
import com.dzhtv.rickandmortylibrary.presentation.adapter.RecyclerItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesListFragment : BaseFragment() {

    @Inject
    lateinit var fragmentAdapter: CharacterGridAdapter

    private var _binding: FragmentCharacterListBinding? = null
    private val binding: FragmentCharacterListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "FavoritesListFragment create")
        initUI()
    }

    private fun initUI() {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = fragmentAdapter
            addOnItemTouchListener(
                RecyclerItemClickListener(
                    context,
                    recyclerView = this
                ) { view, position ->
                    // TODO
                }
            )
        }
        binding.recyclerView.isVisible = false
        binding.emptyView.isVisible = true
    }
}