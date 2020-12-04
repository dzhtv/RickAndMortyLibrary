package com.dzhtv.rickandmortylibrary.ui.character_list

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dzhtv.rickandmortylibrary.Event
import com.dzhtv.rickandmortylibrary.adapter.CharacterGridAdapter
import com.dzhtv.rickandmortylibrary.base.BaseViewModel
import com.dzhtv.rickandmortylibrary.log
import com.dzhtv.rickandmortylibrary.model.BaseResponse
import com.dzhtv.rickandmortylibrary.model.Character
import com.dzhtv.rickandmortylibrary.network.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CharacterListViewModel @ViewModelInject constructor(
    private val networkRepo: NetworkRepository,
    private val adapter: CharacterGridAdapter
) : BaseViewModel() {

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage
    var isLoadingProgress = MutableLiveData(View.GONE)
    private var characters = MutableLiveData(listOf<Character>())
    private var nextPage: Int? = null

    override fun handlerError(t: Throwable) {
        t.message?.let {
            _errorMessage.value = Event(it)
        }
    }

    fun getCharacterAdapter(): CharacterGridAdapter {
        return adapter
    }

    fun fetchCharacters() {
        if (characters.value.isNullOrEmpty())
            loadCharacters()
    }

    private fun loadCharacters(page: Int? = null) {
        isLoadingProgress.value = View.VISIBLE
        coroutineScope.launch {
            val deferred = async(Dispatchers.IO) {
                networkRepo.getCharacters(page, null)
            }
            deferred.await()?.let {
                handleResponse(it)
            }
            isLoadingProgress.value = View.GONE
        }
    }

    private fun handleResponse(result: BaseResponse<Character>) {
        characters.value = result.results?.apply {
            updateCharacterAdapter(this)
        }
        nextPage = result.info?.next?.substringAfterLast("page=")?.toInt()
    }

    private fun updateCharacterAdapter(characters: List<Character>) {
        if (characters.isNotEmpty()) {
            adapter.apply {
                addItems(characters)
                notifyDataSetChanged()
            }
        }
    }

    fun onScrolledRecyclerToEnd() {
        if (nextPage != null && isLoadingProgress.value == View.GONE)
            loadCharacters(nextPage)
    }
}