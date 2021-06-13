package com.dzhtv.rickandmortylibrary.presentation.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.di.GlideApp


object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: AppCompatImageView, url: String) {
        GlideApp.with(view.context)
            .load(url)
            .fitCenter()
            .error(ContextCompat.getDrawable(view.context, R.drawable.ic_not_found_image_24))
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

    @JvmStatic
    @BindingAdapter("isGoneIfEmpty")
    fun visibility(view: AppCompatTextView, value: Any?) {
        view.visibility =
            if (value != null) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }
}