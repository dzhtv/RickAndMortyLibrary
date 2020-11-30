package com.dzhtv.rickandmortylibrary.base

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private var isShowingToast = false

    protected fun showToast(message: String) {
        if (!isShowingToast && message.isNotEmpty()) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}