package com.example.webeteer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webeteer.app.common.Resource
import com.example.webeteer.domain.repository.MovieRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _homeState = Channel<HomeState>()
    val homeState = _homeState.receiveAsFlow()

    init {
        getMovieList()
    }

    private fun getMovieList() = viewModelScope.launch {
        movieRepository.getMoviesList().collect { state ->
            when (state) {
                is Resource.Loading -> _homeState.send(HomeState.Loading)
                is Resource.Success -> _homeState.send(HomeState.SuccessGenreList(state.value))
                is Resource.Error -> _homeState.send(HomeState.Error(state.error))
            }
        }
    }
}