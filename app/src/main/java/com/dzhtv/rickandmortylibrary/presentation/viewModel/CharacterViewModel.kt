package com.dzhtv.rickandmortylibrary.presentation.viewModel

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dzhtv.rickandmortylibrary.data.model.CharacterResponse
import com.dzhtv.rickandmortylibrary.data.model.Episode
import com.dzhtv.rickandmortylibrary.data.model.ResultWrapper
import com.dzhtv.rickandmortylibrary.data.model.Character
import com.dzhtv.rickandmortylibrary.data.repository.RemoteRepository
import com.dzhtv.rickandmortylibrary.presentation.Event
import com.dzhtv.rickandmortylibrary.presentation.adapter.CharacterGridAdapter
import com.dzhtv.rickandmortylibrary.presentation.base.BaseViewModel
import com.dzhtv.rickandmortylibrary.presentation.merge
import com.dzhtv.rickandmortylibrary.toLog
import kotlinx.coroutines.launch

class CharacterViewModel @ViewModelInject constructor(
    private val networkRepo: RemoteRepository,
    private val adapter: CharacterGridAdapter
) : BaseViewModel() {

    val errorMessage = MutableLiveData<Event<String>>()
    val scrollDown = MutableLiveData<Event<Unit>>()
    var isLoadingProgress = MutableLiveData<Event<Int>>()
    val character =  MutableLiveData<Character>()
    val characterImageUrl = MutableLiveData<String>()
    val characterEpisodeStart = MutableLiveData<Episode>()
    private var characters = MutableLiveData(listOf<Character>())
    private var nextPage: Int? = null

    init {
        "init viewModel".toLog()
    }
    override fun handlerError(t: Throwable) {
        t.message?.let {
            errorMessage.postValue(Event(it))
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
        isLoadingProgress.postValue(Event(View.VISIBLE))
        coroutineScope.launch {
            val result = networkRepo.getCharacters(page, null)
            when(result) {
                is ResultWrapper.NetworkError -> {

                }
                is ResultWrapper.GenericError -> {
                    result.error?.message?.let {
                        errorMessage.postValue(Event(it))
                    }
                }
                is ResultWrapper.Success -> {
                    handleCharacterResponse(result.value)
                }
            }
            isLoadingProgress.postValue(Event(View.GONE))
        }
    }

    private fun handleCharacterResponse(result: CharacterResponse) {
        if (characters.value != null && !result.results.isNullOrEmpty()) {
            characters.value = characters.value?.merge(result.results)
            updateCharacterAdapter(characters.value!!)
        }
        if (nextPage != null)
            scrollDown.postValue(Event(Unit))
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
                        errorMessage.postValue(Event(it))
                    }
                }
            }
        }
    }

    private fun handleEpisodeResponse(value: Episode) {
        characterEpisodeStart.postValue(value)
    }

    fun onScrolledRecyclerToEnd() {
        if (nextPage == null)
            return
        isLoadingProgress.value?.let { event ->
            event.getContentIfNotHandled()?.let { isLoading ->
                if (isLoading == View.GONE)
                    loadCharacters(nextPage)
            }
        } ?: loadCharacters(nextPage)
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
    }
}