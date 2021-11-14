package com.dzhtv.rickandmortylibrary.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val info: RequestInfo,
    val characters: List<CharacterItem>
) : Parcelable
