package com.dzhtv.rickandmortylibrary.presentation.viewModel

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dzhtv.rickandmortylibrary.domain.model.ResultWrapper
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.model.Character
import com.dzhtv.rickandmortylibrary.domain.model.EpisodeItem
import com.dzhtv.rickandmortylibrary.domain.repository.RickAndMortyRemoteRepository
import com.dzhtv.rickandmortylibrary.presentation.Event
import com.dzhtv.rickandmortylibrary.presentation.adapter.CharacterGridAdapter
import com.dzhtv.rickandmortylibrary.presentation.merge
import com.dzhtv.rickandmortylibrary.toLog
import kotlinx.coroutines.launch

class CharacterViewModel @ViewModelInject constructor(
    private val networkRepo: RickAndMortyRemoteRepository,
    private val adapter: CharacterGridAdapter
) : BaseViewModel() {

    val errorMessage = MutableLiveData<Event<String>>()
    val scrollDown = MutableLiveData<Event<Unit>>()
    var isLoadingProgress = MutableLiveData<Event<Int>>()
    val character = MutableLiveData<CharacterItem>()
    val characterImageUrl = MutableLiveData<String>()
    val characterEpisodeStart = MutableLiveData<EpisodeItem>()
    private var characters = MutableLiveData(listOf<CharacterItem>())
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
            val result = networkRepo.getCharactersByFilter(page, null)
            when (result) {
                is ResultWrapper.Success -> {
                    handleCharacterResponse(result.data)
                }
                is ResultWrapper.Error -> {
                    result.error?.message?.let {
                        errorMessage.postValue(Event(it))
                    }
                }
            }
            isLoadingProgress.postValue(Event(View.GONE))
        }
    }

    private fun handleCharacterResponse(result: Character) {
        characters.value = characters.value?.merge(result.characters)
        updateCharacterAdapter(characters.value!!)

        if (nextPage != null)
            scrollDown.postValue(Event(Unit))
        nextPage = result.info.next?.substringAfterLast("page=")?.toInt()
    }

    private fun updateCharacterAdapter(characters: List<CharacterItem>) {
        if (characters.isNotEmpty()) {
            adapter.apply {
                refreshItems(characters)
                notifyDataSetChanged()
            }
        }
    }

    private fun loadCharacter(id: Int) {
        coroutineScope.launch {
            val result = networkRepo.getCharacterById(id)
            when (result) {
                is ResultWrapper.Success -> {
                }
                is ResultWrapper.Error -> {
                }
            }
        }
    }

    private fun loadEpisode(id: Int) {
        coroutineScope.launch {
            val result = networkRepo.getEpisodeById(id)
            when (result) {
                is ResultWrapper.Success -> {
                    handleEpisodeResponse(result.data)
                }
                is ResultWrapper.Error -> {
                    result.error?.message?.let {
                        errorMessage.postValue(Event(it))
                    }
                }
            }
        }
    }

    private fun handleEpisodeResponse(value: EpisodeItem) {
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
            item.image?.let { characterImageUrl.postValue(it) }
            loadEpisode(getFirstAppearanceCharacter(item))
        }
    }

    private fun getFirstAppearanceCharacter(value: CharacterItem): Int {
        return value.episode.first().substringAfterLast("episode/").toInt()
    }
}