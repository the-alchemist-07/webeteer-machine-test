package com.example.webeteer.domain.model

data class Genre(
    val id: String,
    val genre: String,
    val movieList: List<Movie>
)
