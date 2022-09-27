package com.dzhtv.rickandmortylibrary.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dzhtv.rickandmortylibrary.domain.model.CharacterItem
import com.dzhtv.rickandmortylibrary.domain.usecase.AddFavoriteCharacterUseCase
import com.dzhtv.rickandmortylibrary.domain.usecase.BaseFlowUseCase
import com.dzhtv.rickandmortylibrary.domain.usecase.GetFavoritesCharacterUseCase
import com.dzhtv.rickandmortylibrary.domain.usecase.RemoveFavoriteCharacterUseCase
import com.dzhtv.rickandmortylibrary.presentation.Event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoritesViewModel @ViewModelInject constructor(
    private val getFavoritesCharacterUseCase: GetFavoritesCharacterUseCase
) : BaseViewModel() {

    val errorMessage = MutableLiveData<Event<String>>()
    val favoriteCharacterItems = MutableLiveData<List<CharacterItem>>()

    override fun handlerError(t: Throwable) {
        t.message?.let {
            errorMessage.postValue(Event(it))
        }
    }

    private fun refreshFavoriteList() {
        coroutineScope.launch {
            getFavoritesCharacterUseCase.execute(BaseFlowUseCase.EmptyValues()).collect { charactersItems ->
                favoriteCharacterItems.postValue(charactersItems)
            }
        }
    }
}