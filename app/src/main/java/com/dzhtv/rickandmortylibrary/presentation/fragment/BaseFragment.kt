package com.dzhtv.rickandmortylibrary.presentation.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import com.dzhtv.rickandmortylibrary.presentation.viewmodel.CharacterViewModel
import com.dzhtv.rickandmortylibrary.presentation.activity.MainActivity

abstract class BaseFragment : Fragment() {


    protected fun appContext(): Context {
        return requireActivity().applicationContext
    }

    protected fun provideCharacterViewModel(): CharacterViewModel {
        val context = requireActivity()
        if (context is MainActivity) {
            return context.getCharacterViewModel() as CharacterViewModel
        } else {
            throw IllegalArgumentException("not find view model")
        }
    }
}