package com.dzhtv.rickandmortylibrary

import android.util.Log


fun String.toLog() {
    Log.d("[RickAndMorty]", this)
}

fun log(message: String) {
    Log.d("[RickAndMorty]", message)
}