package com.example.webeteer.ui.home

import com.example.webeteer.domain.model.Genre

sealed class HomeState {
    data class SuccessGenreList(val genreList: List<Genre>) : HomeState()
    data class Error(val message: String) : HomeState()
    data object Loading : HomeState()
}
