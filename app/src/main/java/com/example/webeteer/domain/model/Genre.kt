package com.example.webeteer.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val id: String,
    val genre: String,
    val movieList: List<Movie>
) : Parcelable