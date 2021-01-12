package com.dzhtv.rickandmortylibrary.presentation.base

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.presentation.viewModel.CharacterViewModel
import com.dzhtv.rickandmortylibrary.presentation.ui.MainActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    private var isShowingToast = false

    protected fun showToast(message: String) {
        if (!isShowingToast && message.isNotEmpty()) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    protected fun showSnackbarRetryAction(view: View, message: String, block: ()-> Unit) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction(requireActivity().getString(R.string.retry)) {
                block.invoke()
            }
            .show()
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