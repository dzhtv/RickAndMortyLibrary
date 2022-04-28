package com.dzhtv.rickandmortylibrary.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dzhtv.rickandmortylibrary.domain.usecase.AddFavoriteCharacterUseCase
import com.dzhtv.rickandmortylibrary.domain.usecase.GetFavoritesCharacterUseCase
import com.dzhtv.rickandmortylibrary.domain.usecase.RemoveFavoriteCharacterUseCase
import com.dzhtv.rickandmortylibrary.presentation.Event

class FavoritesViewModel @ViewModelInject constructor(
    private val getFavoritesCharacterUseCase: GetFavoritesCharacterUseCase,
    private val addFavoritesCharacterUseCase: AddFavoriteCharacterUseCase,
    private val removeFavoriteCharacterUseCase: RemoveFavoriteCharacterUseCase
) : BaseViewModel() {

    val errorMessage = MutableLiveData<Event<String>>()

    override fun handlerError(t: Throwable) {
        t.message?.let {
            errorMessage.postValue(Event(it))
        }
    }
}