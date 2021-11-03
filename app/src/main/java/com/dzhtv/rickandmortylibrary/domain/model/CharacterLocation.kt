package com.dzhtv.rickandmortylibrary.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterLocation(
    val name: String,
    val url: String
) : Parcelable
