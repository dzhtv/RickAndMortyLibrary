package com.dzhtv.rickandmortylibrary.presentation.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.dzhtv.rickandmortylibrary.data.GlideApp


object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: AppCompatImageView, url: String) {
        GlideApp.with(view.context)
            .load(url)
            .fitCenter()
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("isGoneIfEmpty")
    fun visibility(view: AppCompatTextView, value: String?) {
        view.visibility =
            if (value != null && value.isNotEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }
}