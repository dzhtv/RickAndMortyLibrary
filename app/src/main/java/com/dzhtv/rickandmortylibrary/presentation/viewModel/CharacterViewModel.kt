package com.dzhtv.rickandmortylibrary.presentation.viewModel

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dzhtv.rickandmortylibrary.data.model.*
import com.dzhtv.rickandmortylibrary.presentation.Event
import com.dzhtv.rickandmortylibrary.presentation.adapter.CharacterGridAdapter
import com.dzhtv.rickandmortylibrary.presentation.base.BaseViewModel
import com.dzhtv.rickandmortylibrary.presentation.log
import com.dzhtv.rickandmortylibrary.presentation.merge
import com.dzhtv.rickandmortylibrary.data.repository.RemoteRepository
import kotlinx.coroutines.launch

class CharacterViewModel @ViewModelInject constructor(
    private val networkRepo: RemoteRepository,
    private val adapter: CharacterGridAdapter
) : BaseViewModel() {

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage
    private val _scrollDown = MutableLiveData<Event<Unit>>()
    val scrollDown: LiveData<Event<Unit>>
        get() = _scrollDown
    var isLoadingProgress = MutableLiveData(View.GONE)
    private var characters = MutableLiveData(listOf<Character>())
    private var nextPage: Int? = null
    val character: MutableLiveData<Character> = MutableLiveData()
    val characterImageUrl: MutableLiveData<String> = MutableLiveData()
    val characterEpisodeStart: MutableLiveData<Episode> = MutableLiveData()

    init {
        log("init view model")
    }
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
            val result = networkRepo.getCharacters(page, null)
            when(result) {
                is ResultWrapper.NetworkError -> {

                }
                is ResultWrapper.GenericError -> {
                    result.error?.message?.let {
                        _errorMessage.value = Event(it)
                    }
                }
                is ResultWrapper.Success -> {
                    handleCharacterResponse(result.value)
                }
            }
            isLoadingProgress.postValue(View.GONE)
        }
    }

    private fun handleCharacterResponse(result: CharacterResponse) {
        if (characters.value != null && !result.results.isNullOrEmpty()) {
            characters.value = characters.value?.merge(result.results)
            updateCharacterAdapter(characters.value!!)
        }
        if (nextPage != null)
            _scrollDown.value = Event(Unit)
        nextPage = result.info?.next?.substringAfterLast("page=")?.toInt()
    }

    private fun updateCharacterAdapter(characters: List<Character>) {
        if (characters.isNotEmpty()) {
            adapter.apply {
                refreshItems(characters)
                notifyDataSetChanged()
            }
        }
    }

    private fun loadCharacter(id: Int) {
        coroutineScope.launch {
            val result = networkRepo.getCharacter(id)
            when(result) {
                is ResultWrapper.Success -> {}
                is ResultWrapper.GenericError -> {}
                is ResultWrapper.NetworkError -> {}
            }
        }
    }

    private fun loadEpisode(id: Int) {
        coroutineScope.launch {
            val result = networkRepo.getEpisode(id)
            when(result) {
                is ResultWrapper.Success -> {
                    handleEpisodeResponse(result.value)
                }
                is ResultWrapper.GenericError -> {
                    result.error?.message?.let {
                        _errorMessage.value = Event(it)
                    }
                }
            }
        }
    }

    private fun handleEpisodeResponse(value: Episode) {
        characterEpisodeStart.postValue(value)
    }

    fun onScrolledRecyclerToEnd() {
        if (nextPage != null && isLoadingProgress.value == View.GONE)
            loadCharacters(nextPage)
    }

    fun clickOnPosition(position: Int) {
        characters.value?.get(position)?.let { item ->
            character.postValue(item)
            characterImageUrl.postValue(item.image)
            loadEpisode(getFirstAppearanceCharacter(item))
        }
    }

    private fun getFirstAppearanceCharacter(value: Character): Int {
        return value.episode.first().substringAfterLast("episode/").toInt()
    }

    override fun onCleared() {
        super.onCleared()
        characters.postValue(null)
        character.postValue(null)
        characterImageUrl.postValue(null)
        characterEpisodeStart.postValue(null)
    }
}