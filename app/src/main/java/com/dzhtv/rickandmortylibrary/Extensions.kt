package com.dzhtv.rickandmortylibrary

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.lang.RuntimeException

fun Context?.toast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    this.let {
        Toast.makeText(it, text, duration).show()
    }
}

fun View.hideKeyboard(): Boolean {
    try {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.hideSoftInputFromWindow(windowToken, 0)
    } catch (ex: RuntimeException) { }
    return false
}

fun <T>Iterable<T>.merge(collection: Iterable<T>): List<T> {
    val mutable = mutableListOf<T>()
    mutable.addAll(this)
    mutable.addAll(collection)
    return mutable.toList()
}

fun <T>fetchAsync(block: suspend () -> T) {

}