package com.example.webeteer.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.webeteer.domain.model.Movie

class MovieDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val movieData = savedStateHandle.get<Movie>("movieData")

}