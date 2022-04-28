package com.dzhtv.rickandmortylibrary

import android.util.Log

const val TAG = "[RickAndMorty]"


fun String.toLog() {
    Log.d("[RickAndMorty]", this)
}

fun log(message: String) {
    Log.d("[RickAndMorty]", message)
}