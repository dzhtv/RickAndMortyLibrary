package com.dzhtv.rickandmortylibrary.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.presentation.gone
import com.dzhtv.rickandmortylibrary.presentation.visible

class CharacterItemFieldView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr, 0) {
        initAttrs(attr, 0)
    }

    constructor(context: Context, attr: AttributeSet, defStyle: Int) : super(context, attr, defStyle) {
        initAttrs(attr, defStyle)
    }

    private lateinit var title: AppCompatTextView
    private lateinit var header: AppCompatTextView
    private lateinit var subheader: AppCompatTextView
    private lateinit var picture: AppCompatImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_character_field, this, true)
        initUI()
    }

    private fun initUI() {
        title = findViewById(R.id.title)
        header = findViewById(R.id.header)
        subheader = findViewById(R.id.subheader)
        picture = findViewById(R.id.picture)
    }

    private fun initAttrs(attr: AttributeSet?, defStyle: Int) {
        attr?.let { atrSet ->
            context.theme.obtainStyledAttributes(atrSet, R.styleable.CharacterItemFieldView, defStyle, 0)
                .apply {
                    try {
                        getString(R.styleable.CharacterItemFieldView_title)?.let { setTitle(it) }
                        getString(R.styleable.CharacterItemFieldView_header)?.let { setHeaderText(it) }
                        getString(R.styleable.CharacterItemFieldView_subheader)?.let { setSubheader(it) }
                        getInteger(R.styleable.CharacterItemFieldView_picture, 0).apply { setPicture(this, isVisible = true) }

                    } finally {
                        recycle()
                    }
                }
        }
    }

    fun setTitle(
        text: String,
        isVisible: Boolean = true,
        textSize: Float = 16f,
    ) {
        this.title.apply {
            setText(text)
            setTextSize(textSize)
            if (isVisible) visible() else gone()
        }
    }

    fun setHeaderText(
        text: String,
        isVisible: Boolean = true,
        textSize: Float = 22f,
    ) {
        this.header.apply {
            setText(text)
            setTextSize(textSize)
            if (isVisible) visible() else gone()
        }
    }

    fun setSubheader(
        text: String,
        isVisible: Boolean = true,
        textSize: Float = 16f,
    ) {
        this.subheader.apply {
            setText(text)
            setTextSize(textSize)
            if (isVisible) visible() else gone()
        }
    }

    fun setPicture(
        @DrawableRes picture: Int = R.drawable.ic_arrow_right_48,
        isVisible: Boolean = false
    ) {
        this.picture.apply {
            setImageResource(picture)
            if (isVisible) visible() else gone()
        }
    }
}
