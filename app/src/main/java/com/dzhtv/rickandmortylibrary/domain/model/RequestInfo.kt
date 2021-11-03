package com.dzhtv.rickandmortylibrary.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestInfo(
    val count: Int,
    val pages: Int,
    val prev: String?,
    val next: String?
) : Parcelable
