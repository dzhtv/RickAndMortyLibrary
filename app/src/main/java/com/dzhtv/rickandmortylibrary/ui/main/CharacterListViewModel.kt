package com.dzhtv.rickandmortylibrary.ui.main

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dzhtv.rickandmortylibrary.adapter.CharacterGridAdapter
import com.dzhtv.rickandmortylibrary.base.BaseViewModel
import com.dzhtv.rickandmortylibrary.log
import com.dzhtv.rickandmortylibrary.model.Character
import com.dzhtv.rickandmortylibrary.network.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CharacterListViewModel @ViewModelInject constructor(
    private val networkRepo: NetworkRepository,
    private val adapter: CharacterGridAdapter
) : BaseViewModel() {

    var isLoadingProgress = MutableLiveData(View.GONE)
    var errorMessage = MutableLiveData("")
    private var characters = MutableLiveData(listOf<Character>())

    override fun handlerError(t: Throwable) {
        t.message?.let {
            errorMessage.value = it
        }
    }

    fun getCharacterAdapter(): CharacterGridAdapter {
        return adapter
    }

    fun fetchCharacters() {
        if (characters.value.isNullOrEmpty())
            loadCharacters()
    }

    fun refreshCharacter() {
        loadCharacters()
    }

    private fun loadCharacters() {
        isLoadingProgress.value = View.VISIBLE
        coroutineScope.launch {
            val deferred = async(Dispatchers.IO) {
                networkRepo.getCharacters(null, null)
            }
            characters.value = deferred.await()
            isLoadingProgress.value = View.GONE
            log("Load characters, characters size: ${characters.value?.size}")
            characters.value?.let {
                updateCharacterAdapter(it)
            }
        }
    }

    private fun updateCharacterAdapter(characters: List<Character>) {
        if (characters.isNotEmpty()) {
            adapter.apply {
                refreshItems(characters)
                notifyDataSetChanged()
            }
        }
    }
}