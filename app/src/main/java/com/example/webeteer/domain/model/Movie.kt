package com.example.webeteer.domain.model

data class Movie(
    val id: String,
    val title: String,
    val description: String,
    val genreList: List<String>,
    val releaseDate: String,
    val posterUrl: String,
    val rating: String
)
