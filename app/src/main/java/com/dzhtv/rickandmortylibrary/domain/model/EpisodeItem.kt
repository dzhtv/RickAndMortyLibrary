package com.dzhtv.rickandmortylibrary.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeItem(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) : Parcelable
