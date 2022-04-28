package com.dzhtv.rickandmortylibrary.presentation.contract

import android.view.View

interface BaseActivityView {
    fun getContainerView(): View?
    fun getFullscreenView(): View?
}