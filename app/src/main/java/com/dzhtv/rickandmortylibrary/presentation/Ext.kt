package com.dzhtv.rickandmortylibrary.presentation

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.di.GlideApp
import com.google.android.material.snackbar.Snackbar
import java.lang.RuntimeException

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Context?.showToast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    this.let {
        Toast.makeText(it, text, duration).show()
    }
}

fun View.hideKeyboard(): Boolean {
    try {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.hideSoftInputFromWindow(windowToken, 0)
    } catch (ex: RuntimeException) {
    }
    return false
}

fun View.showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun View.showSnackbarWithRetryAction(
    message: String,
    duration: Int = Snackbar.LENGTH_LONG,
    @StringRes actionText: Int = R.string.retry,
    block: () -> Unit
) {
    Snackbar.make(this, message, duration).setAction(actionText) {
        block.invoke()
    }.show()
}

fun AppCompatImageView.loadImage(url: String) {
    GlideApp.with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}

fun <T> Iterable<T>.merge(collection: Iterable<T>): List<T> {
    val mutable = mutableListOf<T>()
    mutable.addAll(this)
    mutable.addAll(collection)
    return mutable.toList()
}

fun <T: Any> ArrayList<T>.addOnlyNewItems(items: List<T>): ArrayList<T> {
    items.forEach { if (!this.contains(it)) this.add(it) }
    return this
}