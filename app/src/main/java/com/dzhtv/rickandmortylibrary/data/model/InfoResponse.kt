package com.dzhtv.rickandmortylibrary.data.model

data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
