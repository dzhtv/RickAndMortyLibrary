package com.dzhtv.rickandmortylibrary.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterFilter(
    val info: RequestInfo,
    val characters: List<Character>
) : Parcelable
