package com.dzhtv.rickandmortylibrary.presentation.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.dzhtv.rickandmortylibrary.presentation.viewModel.CharacterViewModel
import com.dzhtv.rickandmortylibrary.presentation.ui.MainActivity

abstract class BaseFragment : Fragment() {


    protected fun appContext(): Context {
        return requireActivity().applicationContext
    }

    protected fun provideCharacterViewModel(): CharacterViewModel {
        val context = requireContext()
        if (context is MainActivity) {
            return context.getCharacterViewModel()
        } else {
            throw IllegalArgumentException("not find view model")
        }
    }
}