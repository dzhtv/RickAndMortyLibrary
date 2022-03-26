package com.dzhtv.rickandmortylibrary.presentation.viewmodel

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dzhtv.rickandmortylibrary.domain.model.ResultWrapper
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.model.Character
import com.dzhtv.rickandmortylibrary.domain.model.EpisodeItem
import com.dzhtv.rickandmortylibrary.domain.usecase.GetCharacterListUseCase
import com.dzhtv.rickandmortylibrary.domain.usecase.GetEpisodeByIdUseCase
import com.dzhtv.rickandmortylibrary.presentation.Event
import com.dzhtv.rickandmortylibrary.presentation.merge
import com.dzhtv.rickandmortylibrary.toLog
import kotlinx.coroutines.launch

class CharacterViewModel @ViewModelInject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase
) : BaseViewModel() {

    val errorMessage = MutableLiveData<Event<String>>()
    val scrollDown = MutableLiveData<Event<Unit>>()
    var isLoadingProgress = MutableLiveData<Event<Int>>()
    val character = MutableLiveData<CharacterItem>()
    val characterImageUrl = MutableLiveData<String>()
    var characters = MutableLiveData<List<CharacterItem>>(emptyList())
    private var nextPage: Int? = null

    init {
        "init viewModel".toLog()
    }

    override fun handlerError(t: Throwable) {
        t.message?.let {
            errorMessage.postValue(Event(it))
        }
    }

    fun fetchCharacters() {
        if (characters.value.isNullOrEmpty())
            loadCharacters()
    }

    fun onScrolledRecyclerToEnd() {
        if (nextPage == null) return
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
            item.image.let { characterImageUrl.postValue(it) }
            loadEpisode(getFirstAppearanceCharacter(item))
        }
    }

    private fun loadCharacters(page: Int? = null) {
        isLoadingProgress.postValue(Event(View.VISIBLE))
        coroutineScope.launch {
            val result = getCharacterListUseCase.execute(
                GetCharacterListUseCase.RequestValues(page)
            )
            when (result) {
                is ResultWrapper.Success -> handleCharacterResponse(result.data)
                is ResultWrapper.Error -> result.error.message.let {
                    errorMessage.postValue(Event(it))
                }
            }
            isLoadingProgress.postValue(Event(View.GONE))
        }
    }

    private fun handleCharacterResponse(result: Character) {
        val currentCharacters = characters.value ?: return

        characters.postValue(
            currentCharacters.merge(result.characters)
        )
        if (nextPage != null) scrollDown.postValue(Event(Unit))
        nextPage = result.info.next?.substringAfterLast("page=")?.toInt()
    }

    private fun loadEpisode(id: Int) {
        coroutineScope.launch {
            val result = getEpisodeByIdUseCase.execute(
                GetEpisodeByIdUseCase.RequestValues(id)
            )
            when (result) {
                is ResultWrapper.Success -> {
                    character.value?.let {
                        it.firstEpisodeItem = result.data
                        character.postValue(it)
                    }
                }
                is ResultWrapper.Error -> result.error.message.let {
                    errorMessage.postValue(Event(it))
                }
            }
        }
    }

    private fun getFirstAppearanceCharacter(value: CharacterItem): Int {
        return value.episode.first().substringAfterLast("episode/").toInt()
    }
}