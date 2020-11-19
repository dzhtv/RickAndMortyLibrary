package com.dzhtv.rickandmortylibrary.ui.main

import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dzhtv.rickandmortylibrary.base.BaseViewModel
import com.dzhtv.rickandmortylibrary.model.Character
import com.dzhtv.rickandmortylibrary.network.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel @ViewModelInject constructor(
    private val networkRepo: NetworkRepository
) : BaseViewModel() {

    var isLoadingProgress = MutableLiveData(View.GONE)
    var errorMessage = MutableLiveData("")
    var characters = MutableLiveData(listOf<Character>())

    override fun handlerError(t: Throwable) {
        t.message?.let {
            errorMessage.value = it
        }
    }

    fun fetchCharacters() {
        isLoadingProgress.value = View.VISIBLE
        coroutineScope.launch {
            val deferred = async(Dispatchers.IO) {
                networkRepo.getCharacters(null, null)
            }
            characters.value = deferred.await()
            isLoadingProgress.value = View.GONE
            Log.d("dzhtv", "Characters size: ${characters.value?.size}")
        }
    }

    fun refreshCharacter() {}
}