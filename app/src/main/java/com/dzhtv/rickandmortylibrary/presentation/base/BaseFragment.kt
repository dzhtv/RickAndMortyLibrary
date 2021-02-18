package com.dzhtv.rickandmortylibrary.presentation.base

import androidx.fragment.app.Fragment
import com.dzhtv.rickandmortylibrary.presentation.viewModel.CharacterViewModel
import com.dzhtv.rickandmortylibrary.presentation.ui.MainActivity

abstract class BaseFragment : Fragment() {

    protected fun provideCharacterViewModel(): CharacterViewModel {
        val context = requireActivity()
        if (context is MainActivity) {
            return context.getCharacterViewModel() as CharacterViewModel
        } else {
            throw IllegalArgumentException("not find view model")
        }
    }
}