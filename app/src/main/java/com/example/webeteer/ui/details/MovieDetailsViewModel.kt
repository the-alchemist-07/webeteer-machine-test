package com.example.webeteer.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.webeteer.domain.model.Movie

class MovieDetailsViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val movieData = savedStateHandle.get<Movie>("movieData")

}