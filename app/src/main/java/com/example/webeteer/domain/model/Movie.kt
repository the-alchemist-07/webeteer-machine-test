package com.example.webeteer.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val genreList: List<String>,
    val releaseDate: String,
    val posterUrl: String,
    val rating: String
) : Parcelable